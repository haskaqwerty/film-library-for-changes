package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;

import java.util.List;

public interface MovieDao {

     Movie getMovieById(Integer id);

    List<Movie> getAll();
    boolean create(Movie movie);
    boolean update(Movie movie, Integer id);
    boolean delete(Integer id);


}
