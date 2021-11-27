package com.example.mp3.controller;

import com.example.mp3.model.Song;
import com.example.mp3.model.SongForm;
import com.example.mp3.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
@CrossOrigin("*")
public class SongController {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<Iterable<Song>> findAll() {
        Iterable<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("songs")
    public ResponseEntity<Iterable<Song>> findAll(@RequestParam("page") Long page) {
        Long size = new Long(2);
        Iterable<Song> songs = songService.findSongsByPage(size, page * size);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Song> findById(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (song.isPresent()) {
            return new ResponseEntity<>(song.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(SongForm songForm) {
        MultipartFile file = songForm.getFile();
        MultipartFile image = songForm.getImage();
        String imageName = image.getOriginalFilename();
        String fileName = file.getOriginalFilename();
        Song song = new Song();
        song.setName(songForm.getName());
        song.setSinger(songForm.getSinger());
        song.setCategory(songForm.getCategory());
        song.setFile(fileName);
        song.setImage(imageName);
        try {
            byte[] bytes = file.getBytes();
            File fileSong = new File(fileUpload + "songs/" + fileName);
            FileCopyUtils.copy(bytes, fileSong);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
            File fileImage = new File(fileUpload + "images/" + imageName);
            FileCopyUtils.copy(bytes, fileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<Song> edit(@PathVariable Long id, Song song) {
        Optional<Song> songOptional = songService.findById(id);
        if (songOptional.isPresent()) {
            song.setId(id);
            return new ResponseEntity<>(songService.save(song), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Song> delete(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (song.isPresent()) {
            songService.deleteById(id);
            return new ResponseEntity<>(song.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
