package com.example.mp3.service;

import java.util.Optional;

public interface IGeneralService<T> {

    Iterable<T> findAll();

    T save(T t);

    void deleteById(Long id);

    Optional<T> findById(Long id);

}
