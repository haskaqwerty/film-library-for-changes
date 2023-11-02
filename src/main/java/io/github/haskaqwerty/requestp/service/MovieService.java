package io.github.haskaqwerty.requestp.service;

import io.github.haskaqwerty.requestp.pojo.Movie;

import java.util.List;

public interface MovieService {
    Movie create(Movie movie);
    List<Movie> getAll();
    Movie get(int id);
    Movie update(Movie movie, int id);
    Movie delete(int id);


}
