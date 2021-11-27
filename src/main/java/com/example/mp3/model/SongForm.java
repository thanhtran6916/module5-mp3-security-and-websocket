package com.example.mp3.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SongForm {

    private Long id;

    private String name;

    private String singer;

    private Category category;

    private MultipartFile file;

    private MultipartFile image;

    public SongForm() {
    }
}
