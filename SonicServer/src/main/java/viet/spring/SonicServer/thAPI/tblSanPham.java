//package viet.spring.SonicServer.thAPI;
//
//import java.util.List;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//@Entity
//public class tblSanPham {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer MaSP;
//	private String TenSP;
//	private String MoTa;
//	private Long GiaNhap;
//	private Long GiaBan;
//	private  Integer SoLuong;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "chat_lieu")
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	private tblChatLieu chatLieu;
//	
//
//}
