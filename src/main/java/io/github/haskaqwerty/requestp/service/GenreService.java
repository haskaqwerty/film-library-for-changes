package io.github.haskaqwerty.requestp.service;

import io.github.haskaqwerty.requestp.pojo.Genre;

import java.util.List;

public interface GenreService {
    Genre create(Genre genre);
    List<Genre> getAll();
    Genre get(int id);
    Genre update(Genre genre, int id);
    Genre delete(int id);


}
