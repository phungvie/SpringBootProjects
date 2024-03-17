package viet.spring.SonicServer;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import viet.spring.SonicServer.entity.Artist;
import viet.spring.SonicServer.entity.Playlist;
import viet.spring.SonicServer.entity.Role;
import viet.spring.SonicServer.entity.Song;
import viet.spring.SonicServer.entity.User;
import viet.spring.SonicServer.repository.ArtistRepository;
import viet.spring.SonicServer.repository.PlaylistRepository;
import viet.spring.SonicServer.repository.RoleRepository;
import viet.spring.SonicServer.repository.SongRepository;
import viet.spring.SonicServer.repository.UserRepository;

@SpringBootApplication
@AllArgsConstructor
public class SonicServerApplication implements CommandLineRunner {
	UserRepository userR;
	RoleRepository roleR;
	BCryptPasswordEncoder encoder;
	ArtistRepository artistR;
	SongRepository songR;
	PlaylistRepository playlistR;

	public static void main(String[] args) {
		SpringApplication.run(SonicServerApplication.class, args);

	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//
////Các quyền
//		Role role0 = Role.builder().name("USER").build();
//		Role role1 = Role.builder().name("ADMIN").build();
//		Role role2 = Role.builder().name("AUTHENTICATED").build();
//		roleR.saveAll(() -> Lists.newArrayList(role0, role1, role2).iterator());
////
//		
////Người dùng
//		User sonic = User.builder().name("Sonic Admin").password(encoder.encode("123")).mail("sonic").build();
//		sonic.setRoles(Lists.newArrayList(role0, role1, role2));
//		userR.save(sonic);
////
//
////Nhạc sĩ		
//		Artist artistSonTung = Artist.builder().name("Sơn Tùng M-TP").image("/data/img/anh1.jpg").build();
//		Artist artistPhuongLy = Artist.builder().name("Phương Ly").image("/data/img/anh1.jpg").build();
//		Artist artistBichPhuong = Artist.builder().name("Bích Phương").image("/data/img/anh1.jpg").build();
//		Artist artistJustaTee = Artist.builder().name("JustaTee").image("/data/img/anh1.jpg").build();
//
//		artistR.saveAll(
//				() -> Lists.newArrayList(artistSonTung, artistPhuongLy, artistBichPhuong, artistJustaTee).iterator());
////
//		
////Bài hát
//		Song songBGLC = Song.builder().name("Bao Giờ Lấy Chồng").build();// Bích Phương
//		songBGLC.setSound("/data/stream/BaoGioLayChongMRKVT2018Remix-DJ-5385923.mp3");
//		songBGLC.setImage("/data/img/anh1.jpg");
//		songBGLC.setArtist(artistBichPhuong);
//
//		Song songHTCA = Song.builder().name("Hãy Trao Cho Anh").build();
//		songHTCA.setSound("/data/stream/HayTraoChoAnh-SonTungMTPSnoopDogg-6010660.mp3");
//		songHTCA.setImage("/data/img/anh1.jpg");
//		songHTCA.setArtist(artistSonTung);
//
//		Song songMTCE = Song.builder().name("Mặt Trời Của Em").build();// Phương Ly
//		songMTCE.setSound("/data/stream/MatTroiCuaEmKynbbRemix-JustaTeePhuongLy-5290457.mp3");
//		songMTCE.setImage("/data/img/anh1.jpg");
//		songMTCE.setArtist(artistPhuongLy);
//
//		Song songMNMBA = Song.builder().name("Một Năm Mới Bình An").build();
//		songMNMBA.setSound("/data/stream/MotNamMoiBinhAn-SonTungMTP-4315569.mp3");
//		songMNMBA.setImage("/data/img/anh1.jpg");
//		songMNMBA.setArtist(artistSonTung);
//
//		Song songNNCA = Song.builder().name("Nơi Này Có Anh").build();
//		songNNCA.setSound("/data/stream/NoiNayCoAnhTropicalHouseRemix-SonTungMTP-4773696.mp3");
//		songNNCA.setImage("/data/img/anh1.jpg");
//		songNNCA.setArtist(artistSonTung);
//
//		Song songCCBQ = Song.builder().name("Chuyện Cũ Bỏ Qua").build();// Bích Phương
//		songCCBQ.setSound("/data/stream/ChuyenCuBoQua_pyelss0ioh.mp3");
//		songCCBQ.setImage("/data/img/anh1.jpg");
//		songCCBQ.setArtist(artistBichPhuong);
//
//		Song songTCNNT = Song.builder().name("Từ Chối Nhẹ Nhàng Thôi").build();// Bích Phương
//		songTCNNT.setSound("/data/stream/TuChoiNheNhangThoi-BichPhuongPhucDu-6281296.mp3");
//		songTCNNT.setImage("/data/img/anh1.jpg");
//		songTCNNT.setArtist(artistBichPhuong);
//
//		Song songTD = Song.builder().name("Thằng Điên").build();// JustaTee và Phương Ly
//		songTD.setSound("/data/stream/ThangDienLive-JustaTeePhuongLy-6066987.mp3");
//		songTD.setImage("/data/img/anh1.jpg");
//		songTD.setArtist(artistJustaTee);
//
//		songR.saveAllAndFlush(
//				() -> Lists.newArrayList(songBGLC, songHTCA, songMTCE, songMNMBA, songNNCA, songCCBQ, songTCNNT, songTD)
//						.iterator());
//		
////		
//		
////Playlist
//		Playlist playlist1 = Playlist.builder().name("Tập các bài hát của Sơn Tùng").image("/data/img/anh1.jpg")
//				.build();
//		playlist1.setUser(sonic);
//		playlist1.setSongs(Lists.newArrayList(songHTCA, songMNMBA, songNNCA));
//		playlistR.saveAndFlush(playlist1);
//
//		
//		Playlist playlist2 = Playlist.builder().name("Tập các bài hát của Bích Phương").image("/data/img/anh1.jpg")
//				.build();
//		playlist2.setUser(sonic);
//		playlist2.setSongs(Lists.newArrayList(songBGLC, songCCBQ));
//		playlistR.saveAndFlush(playlist2);
////
//		
////Thêm trong thư viện
//		sonic.setLibraryArtist(Lists.newArrayList(artistSonTung, artistPhuongLy));
//		sonic.setLibraryPlaylist(Lists.newArrayList(playlist1));
//		userR.save(sonic);
////
//
	}

}
