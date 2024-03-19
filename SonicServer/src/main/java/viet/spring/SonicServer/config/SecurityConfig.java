package viet.spring.SonicServer.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.transaction.Transactional;
import viet.spring.SonicServer.entity.Role;
import viet.spring.SonicServer.entity.User;
import viet.spring.SonicServer.jwt.JwtAuthenticationFilter;
import viet.spring.SonicServer.service.UserService;
import org.springframework.security.config.annotation.web.configuration.*;

/**
 * SecurityConfig
 * 
 * isAuthenticated(): Kiểm tra xem người dùng đã được xác thực hay chưa. Ví dụ:
 * isAuthenticated() sẽ kiểm tra xem người dùng đã được xác thực hay chưa .
 */
@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig {
	@Autowired
	private UserService userS;
//	@Autowired
//	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			@Transactional
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> userO = userS.findByUsername(username);

				if (userO.isEmpty()) {
					System.out.println("Không tìm thấy người dùng! " + username);
					throw new UsernameNotFoundException(
							"Người dùng " + username + " không được tìm thấy trong cơ sở dữ liệu");
				}

				User user = userO.get();
				List<Role> roles = user.getRoles();
				List<String> names = roles.stream().map(t -> t.getName()).toList();

				List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
				if (names != null) {
					for (String name : names) {
						GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + name);
						grantList.add(authority);
					}
				}

				UserDetails userDetails = new UserImplUserDetails(username, user.getPassword(), grantList);

				return userDetails;
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
			throws Exception {
		http.csrf(t -> t.disable()).authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/sonic/**","/data/**","/security/getUser").hasAnyRole("USER","ADMIN")
				.anyRequest().permitAll())
		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
	//		.exceptionHandling(t -> t.accessDeniedPage("/403"))

		// Cấu hình cho Login Form.
//			.formLogin(t -> t.loginProcessingUrl("/j_spring_security_check") // Submit URL
//					.loginPage("/DangNhap")//
//					.defaultSuccessUrl("/")//
//					.failureUrl("/DangNhap?LoiDangNhap=true")//
//					.usernameParameter("tenDangNhap")//
//					.passwordParameter("matKhau"))

		// Cấu hình cho Logout Page.
//			.logout(t -> t.logoutUrl("/api/"))

		// Cấu hình Remember Me.

//			.rememberMe(t -> t.tokenRepository(this.persistentTokenRepository())
//				.tokenValiditySeconds(1*24*60*60))//24h
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}

}
