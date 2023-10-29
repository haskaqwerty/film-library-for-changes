package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;

import java.util.List;

public interface MovieService {
    boolean create(Movie movie);
    List<Movie> readAll();
    Movie read(int id);
    boolean update(Movie movie, int id);
    boolean delete(int id);


}
