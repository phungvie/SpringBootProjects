//package viet.spring.SonicServer.thAPI;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import io.lettuce.core.dynamic.annotation.Param;
//import viet.spring.SonicServer.entity.Playlist;
//
//public interface sanPhamRepository extends JpaRepository<tblSanPham, Integer>{
//	@Query("FROM tblSanPham p WHERE p.TenSP LIKE %:keyword%")
//	List<tblSanPham> findByKeyword(@Param("key") String keyword);
//	@Query("FROM tblSanPham p WHERE p.SoLuong > 0")
//	List<tblSanPham> findBySoLuong0();
//}
