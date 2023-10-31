package io.github.haskaqwerty.requestp.service;

import io.github.haskaqwerty.requestp.dao.GenreDaoImpl;
import io.github.haskaqwerty.requestp.pojo.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    GenreDaoImpl genreDao = new GenreDaoImpl();
    @Override
    public Genre create(Genre genre) {
        return genreDao.create(genre);

    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genreList = new ArrayList<>(genreDao.getAll());
        return genreList;
    }

    @Override
    public Genre get(int id) {
        return genreDao.getGenreById(id);
    }

    @Override
    public Genre update(Genre genre, int id) {
        return genreDao.update(genre, id);
    }

    @Override
    public Genre delete(int id) {
        return genreDao.delete(id);
    }
}
