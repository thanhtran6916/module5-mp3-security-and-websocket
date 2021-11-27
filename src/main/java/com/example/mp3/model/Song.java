package com.example.mp3.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String singer;

    @ManyToOne
    private Category category;

    private String file;

    private String image;

    public Song() {
    }
}
