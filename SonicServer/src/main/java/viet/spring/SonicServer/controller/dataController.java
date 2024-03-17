package viet.spring.SonicServer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/data")
public class dataController {
	@GetMapping(value = "/img/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImg(@PathVariable String filename) throws IOException {
//		Resource resource = new ClassPathResource("static/" + filename);

		// Đọc dữ liệu từ tài nguyên và trả về trong ResponseEntity
//		byte[] data = Files.readAllBytes(resource.getFile().toPath());
		
		byte[] data = Files.readAllBytes(Paths.get("C:\\dataSonic\\img\\"+filename));

		return ResponseEntity.ok().body(data);

	}
	
	@GetMapping(value = "/stream/{filename:.+}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getStream(@PathVariable String filename) throws IOException {
//		Resource resource = new ClassPathResource("static/" + filename);

		// Đọc dữ liệu từ tài nguyên và trả về trong ResponseEntity
//		byte[] data = Files.readAllBytes(resource.getFile().toPath());
		byte[] data = Files.readAllBytes(Paths.get("C:\\dataSonic\\stream\\"+filename));

		return ResponseEntity.ok().body(data);

	}
}
