package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;

import java.util.List;

public interface GenreService {
    boolean create(Genre genre);
    List<Genre> getAll();
    Genre get(int id);
    boolean update(Genre genre, int id);
    boolean delete(int id);


}
