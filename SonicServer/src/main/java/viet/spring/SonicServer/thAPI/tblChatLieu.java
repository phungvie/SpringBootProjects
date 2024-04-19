//package viet.spring.SonicServer.thAPI;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import viet.spring.SonicServer.entity.Album;
//import viet.spring.SonicServer.entity.Artist;
//import viet.spring.SonicServer.entity.Song;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Entity
//
//public class tblChatLieu {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer MaCL;
//	private String TenCL;
//	@OneToMany(mappedBy = "chatLieu", cascade = CascadeType.ALL)
//	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//	@ToString.Exclude // Khônhg sử dụng trong toString()
//	private List<tblSanPham> listSanPham;
//}
