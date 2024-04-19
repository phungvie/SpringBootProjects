//package viet.spring.SonicServer.thAPI;
//
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.validation.constraints.Positive;
//import lombok.AllArgsConstructor;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@RestController
//@AllArgsConstructor
//public class thAPIController {
//	private sanPhamRepository phamRepository;
//	private chatLieuRepository chatLieuRepository;
//
//	@GetMapping("/getAll/sanPham")
//	public ResponseEntity<List<sanPhamDTO>> getAllSanPham() {
//		List<sanPhamDTO> viet = phamRepository.findAll().stream().map(t -> new sanPhamDTO(t)).toList();
//		return ResponseEntity.ok().body(viet);
//	}
//
//	@GetMapping("/key")
//	public ResponseEntity<List<sanPhamDTO>> key(@RequestParam String key) {
//		List<sanPhamDTO> viet = phamRepository.findByKeyword(key).stream().map(t -> new sanPhamDTO(t)).toList();
//		return ResponseEntity.ok().body(viet);
//	}
//
//	@GetMapping("/soluong")
//	public ResponseEntity<List<sanPhamDTO>> soluong() {
//		List<sanPhamDTO> viet = phamRepository.findBySoLuong0().stream().map(t -> new sanPhamDTO(t)).toList();
//		return ResponseEntity.ok().body(viet);
//	}
//	// Thêm một sản phẩm vào CSDL
//
//	@PostMapping("/themsp")
//	public ResponseEntity<String> themsp(@RequestBody tblSanPham tblSanPham) {
//		if (tblSanPham != null) {
//			phamRepository.save(tblSanPham);
//			return ResponseEntity.ok().body("ok");
//		} else {
//			return ResponseEntity.badRequest().body("tblSanPham null");
//		}
//
//	}
//	//+ Xóa một sản phẩm
//	
//
//	@DeleteMapping("/xoasp")
//	public ResponseEntity<String> xoasp(@RequestBody tblSanPham tblSanPham) {
//		if (tblSanPham != null) {
//			phamRepository.delete(tblSanPham);
//			return ResponseEntity.ok().body("xóa tc");
//		} else {
//			return ResponseEntity.badRequest().body("tblSanPham null");
//		}
//
//	}
//	
//
//}
