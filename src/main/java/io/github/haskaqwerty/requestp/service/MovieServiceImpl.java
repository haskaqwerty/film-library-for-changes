package io.github.haskaqwerty.requestp.service;

import io.github.haskaqwerty.requestp.dao.MovieDaoImpl;
import io.github.haskaqwerty.requestp.pojo.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    MovieDaoImpl movieDao = new MovieDaoImpl();
    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movieList = new ArrayList<>(movieDao.getAll());
        return movieList;
    }

    @Override
    public Movie get(int id) {
        return movieDao.getMovieById(id);
    }

    @Override
    public Movie update(Movie movie, int id) {
        return movieDao.update(movie, id);
    }

    @Override
    public Movie delete(int id) {
        return movieDao.delete(id);

    }
}
