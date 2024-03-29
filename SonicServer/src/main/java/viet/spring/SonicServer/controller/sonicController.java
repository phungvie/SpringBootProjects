package viet.spring.SonicServer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import viet.spring.SonicServer.DTO.ArtistDTO;
import viet.spring.SonicServer.DTO.PlaylistDTO;
import viet.spring.SonicServer.DTO.SongDTO;
import viet.spring.SonicServer.entity.Artist;
import viet.spring.SonicServer.entity.Playlist;
import viet.spring.SonicServer.entity.Song;
import viet.spring.SonicServer.entity.User;
import viet.spring.SonicServer.repository.ArtistRepository;
import viet.spring.SonicServer.repository.PlaylistRepository;
import viet.spring.SonicServer.repository.SongRepository;
import viet.spring.SonicServer.repository.UserRepository;
import viet.spring.SonicServer.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/sonic")
public class sonicController {
	UserService userService;
	UserRepository userR;
	PlaylistRepository playlistR;
	ArtistRepository artistR;
	SongRepository songR;

	//lấy các nghệ sĩ từ thư viện của người dùng
	@GetMapping("/lib/artists")
	public ResponseEntity<List<ArtistDTO>> getLibArtist(Principal vietdz) {
		User viet = userService.findByUsername(vietdz.getName()).orElse(null);
		if (viet != null) {
			// Kiểm tra nếu LibraryArtist không null trước khi trả về
			if (viet.getLibraryArtist() != null) {

				return ResponseEntity.ok().body(viet.getLibraryArtist().stream().map(t -> new ArtistDTO(t)).toList());

			} else {
				// Trả về 404 Not Found nếu không tìm thấy LibraryArtist
				return ResponseEntity.notFound().build();
			}
		} else {
			// Trả về 404 Not Found nếu không tìm thấy người dùng
			return ResponseEntity.notFound().build();
		}
	}

	//lấy các danh sách phát từ thư viện của người dùng
	@GetMapping("/lib/playlists")
	public ResponseEntity<List<PlaylistDTO>> getLibPlaylist(Principal vietdz) {
		User viet = userService.findByUsername(vietdz.getName()).orElse(null);
		if (viet != null) {
			// Kiểm tra nếu LibraryArtist không null trước khi trả về
			if (viet.getLibraryPlaylist() != null) {
				return ResponseEntity.ok()
						.body(viet.getLibraryPlaylist().stream().map(t -> new PlaylistDTO(t)).toList());

			} else {
				// Trả về 404 Not Found nếu không tìm thấy LibraryArtist
				return ResponseEntity.notFound().build();
			}
		} else {
			// Trả về 404 Not Found nếu không tìm thấy người dùng
			return ResponseEntity.notFound().build();
		}
	}
	


	
	
	@GetMapping("/playlist/songs/{id}")
	public ResponseEntity<List<SongDTO>> getSongsInPlaylist(@PathVariable(name = "id") Integer id) {
		Playlist playlist = playlistR.findById(id).orElse(null);
		if (playlist != null) {
			List<Song> songs = playlist.getSongs();
			List<SongDTO> songsDTO = songs.stream().map(t -> new SongDTO(t)).toList();
			return ResponseEntity.ok().body(songsDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	@GetMapping("/artist/songs/{id}")
	public ResponseEntity<List<SongDTO>> getSongsOfArtist(@PathVariable(name = "id") Integer id) {
		Artist artist = artistR.findById(id).orElse(null);
		if (artist != null) {
			List<Song> songs = artist.getSongs();
			List<SongDTO> songsDTO = songs.stream().map(t -> new SongDTO(t)).toList();
			return ResponseEntity.ok().body(songsDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/addArtist")
	public ResponseEntity<String> addArtist(@RequestBody ArtistDTO artistDTO) {
		if (artistDTO == null) {
			return ResponseEntity.badRequest().body("Đối tượng artistDTO là rỗng");
		} else {
			Artist artist = new Artist(artistDTO);
			artistR.save(artist);
			return ResponseEntity.ok().body("Thêm artistDTO thành công");
		}

	}

	@PostMapping("/addSong")
	public ResponseEntity<String> addSong(@RequestBody SongDTO songDTO, @RequestBody Integer i) {
		if (songDTO == null) {
			return ResponseEntity.badRequest().body("Đối tượng songDTO là rỗng");
		} else {
			Song song = new Song(songDTO);
			Artist artist = artistR.findById(i).orElse(null);
			if (artist == null) {
				return ResponseEntity.badRequest()
						.body("Không tìm thấy nghệ sĩ. Nếu chưa thêm nghệ sĩ hãy thêm nghệ sĩ trước");
			} else {
				song.setArtist(artist);
				songR.save(song);
				return ResponseEntity.ok().body("Thêm songDTO thành công");
			}

		}

	}
	
	
	@PostMapping("/addPlaylist")
	public ResponseEntity<String> addPlaylists(@RequestBody PlaylistDTO playlistDTO, Principal vietdz) {
		if (playlistDTO == null) {
			return ResponseEntity.badRequest().body("Đối tượng playlistDTO là rỗng");
		} else {
			Playlist playlist = new Playlist(playlistDTO);
			User user = userService.findByUsername(vietdz.getName()).orElse(null);
			if (user == null) {
				return ResponseEntity.badRequest()
						.body("Không tìm thấy người dùng khởi tạo playlistDTO");
			} else {
				playlist.setUser(user);
				playlistR.save(playlist);
				return ResponseEntity.ok().body("Thêm songDTO thành công");
			}

		}

	}

}
