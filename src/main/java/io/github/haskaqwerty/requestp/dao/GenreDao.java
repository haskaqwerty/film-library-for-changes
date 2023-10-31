package io.github.haskaqwerty.requestp.dao;

import io.github.haskaqwerty.requestp.pojo.Genre;

import java.util.List;

public interface GenreDao {
    Genre getGenreById(Integer id);
    List<Genre> getAll();
    Genre create(Genre genre);
    Genre update(Genre genre, Integer id);
    Genre delete(Integer id);

}
