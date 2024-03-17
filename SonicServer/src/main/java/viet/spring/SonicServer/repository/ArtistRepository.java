package viet.spring.SonicServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import viet.spring.SonicServer.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer>{

}
