package viet.spring.SonicServer.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import viet.spring.SonicServer.DTO.UserDTO;
import viet.spring.SonicServer.config.UserImplUserDetails;
import viet.spring.SonicServer.payload.LoginRequest;
import viet.spring.SonicServer.payload.LoginResponse;
import viet.spring.SonicServer.service.JwtTokenProvider;
import viet.spring.SonicServer.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/security")
@AllArgsConstructor
public class securityController {
	AuthenticationManager authenticationManager;

	private JwtTokenProvider tokenProvider;

	UserService userService;

	UserService vUserService;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// Xác thực từ username và password.
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		UserImplUserDetails viet = new UserImplUserDetails(authentication.getName(), loginRequest.getPassword(),
				(Collection<GrantedAuthority>) authentication.getAuthorities());
		String jwt = tokenProvider.generateToken(viet);
		return new LoginResponse(jwt);

	}

	// Api /api/random yêu cầu phải xác thực mới có thể request
	@GetMapping("/getUser")
	public UserDTO getUser(Principal viet) {
		Optional<viet.spring.SonicServer.entity.User> vietdz = vUserService.findByUsername(viet.getName());
		UserDTO vietDTO=new UserDTO(vietdz.get());
		return vietDTO;

	}

}
