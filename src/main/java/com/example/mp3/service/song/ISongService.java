package com.example.mp3.service.song;

import com.example.mp3.model.Song;
import com.example.mp3.service.IGeneralService;

public interface ISongService extends IGeneralService<Song> {
    Iterable<Song> findSongsByPage(Long limit, Long offset);
}
