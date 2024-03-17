package viet.spring.SonicServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import viet.spring.SonicServer.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

}
