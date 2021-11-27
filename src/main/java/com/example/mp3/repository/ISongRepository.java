package com.example.mp3.repository;

import com.example.mp3.model.Song;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
    @Query(value = "select * from song limit ?1 offset ?2", nativeQuery = true)
    Iterable<Song> findSongsByPage(Long limit, Long offset);
}
