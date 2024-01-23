package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;

import java.util.List;

public interface GenreDao {
    Genre getGenreById(Integer id);
    List<Genre> getAll();
    boolean create(Genre genre);
    boolean update(Genre genre, Integer id);
    boolean delete(Integer id);

}
