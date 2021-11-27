package com.example.mp3.controller;

import com.example.mp3.model.Song;
import com.example.mp3.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class WebsocketController {

    @Autowired
    private ISongService songService;

    @MessageMapping("/songs")
    @SendTo("/topic/song")
    public Song createNewSong(Song song) {
        return songService.save(song);
    }
}
