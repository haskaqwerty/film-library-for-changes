package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.dao.GenreDaoImpl;
import io.github.haskaqwerty.filmlibrary.pojo.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDaoImpl genreDao;
    @Override
    public boolean create(Genre genre) {
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
    public boolean update(Genre genre, int id) {
        return genreDao.update(genre, id);
    }

    @Override
    public boolean delete(int id) {
        return genreDao.delete(id);
    }
}
