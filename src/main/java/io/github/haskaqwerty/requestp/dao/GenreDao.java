package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;

import java.util.List;

public interface GenreDao {
    Genre getGenreById(int id);
    List<Genre> getAll();
    boolean create(Genre genre);
    boolean update(Genre genre,int id);
    boolean delete(int id);

}
