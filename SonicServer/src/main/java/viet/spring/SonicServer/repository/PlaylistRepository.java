package viet.spring.SonicServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import viet.spring.SonicServer.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
