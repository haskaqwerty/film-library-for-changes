package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.dao.MovieDaoImpl;
import io.github.haskaqwerty.filmlibrary.pojo.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDaoImpl movieDao;

    @Override
    public boolean create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movieList = new ArrayList<>(movieDao.getAll());
        return movieList;
    }

    @Override
    public Movie getById(int id) {
        return movieDao.getMovieById(id);
    }

    @Override
    public boolean update(Movie movie, int id) {
        return movieDao.update(movie, id);
    }

    @Override
    public boolean delete(int id) {
        return movieDao.delete(id);

    }
}
