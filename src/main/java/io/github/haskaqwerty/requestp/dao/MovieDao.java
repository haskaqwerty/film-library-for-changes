package io.github.haskaqwerty.requestp.dao;

import io.github.haskaqwerty.requestp.pojo.Movie;

import java.util.List;

public interface MovieDao {

     Movie getMovieById(Integer id);

    List<Movie> getAll();
    Movie create(Movie movie);
    Movie update(Movie movie, Integer id);
    Movie delete(Integer id);


}
