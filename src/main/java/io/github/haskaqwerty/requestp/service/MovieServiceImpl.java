package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.dao.MovieDaoImpl;
import io.github.haskaqwerty.filmlibrary.pojo.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    MovieDaoImpl movieDao = new MovieDaoImpl();
    @Override
    public boolean create(Movie movie) {
        boolean result = movieDao.create(movie);
        return result;
    }

    @Override
    public List<Movie> readAll() {
        List<Movie> movieList = new ArrayList<>();
        movieList = movieDao.getAll();
        System.out.println(movieList.toString());
        return movieList;
    }

    @Override
    public Movie read(int id) {
        Movie movie = new Movie();
        movie= movieDao.getMovieById(id);
        System.out.println(movie.toString());
        return movie;
    }

    @Override
    public boolean update(Movie movie, int id) {
        boolean result = movieDao.update(movie, id);
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = movieDao.delete(id);
        return result;
    }
}
