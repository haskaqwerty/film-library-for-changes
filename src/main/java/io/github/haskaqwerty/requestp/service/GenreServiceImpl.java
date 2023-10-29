package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.dao.GenreDaoImpl;
import io.github.haskaqwerty.filmlibrary.pojo.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    GenreDaoImpl genreDao = new GenreDaoImpl();
    @Override
    public boolean create(Genre genre) {
        boolean result = genreDao.create(genre);
        return result;
    }

    @Override
    public List<Genre> readAll() {
        List<Genre> genreList = new ArrayList<>();
        genreList = genreDao.getAll();
        System.out.println(genreList.toString());
        return genreList;
    }

    @Override
    public Genre read(int id) {
        Genre genre = new Genre();
        genre = genreDao.getGenreById(id);
        System.out.println(genre.toString());
        return genre;
    }

    @Override
    public boolean update(Genre genre, int id) {
        boolean result = genreDao.update(genre, id);
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = genreDao.delete(id);
        return result;
    }
}
