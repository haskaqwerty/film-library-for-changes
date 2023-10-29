package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;

import java.util.List;

public interface MovieDao {

     Movie getMovieById(int id);

    List<Movie> getAll();
    boolean create(Movie movie);
    boolean update(Movie movie,int id);
    boolean delete(int id);


}
