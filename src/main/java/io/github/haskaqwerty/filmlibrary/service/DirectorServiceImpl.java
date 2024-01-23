package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.dao.DirectorDaoImpl;
import io.github.haskaqwerty.filmlibrary.pojo.Director;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{
    DirectorDaoImpl directorDao = new DirectorDaoImpl();
    @Override
    public Director create(Director director) {
        return directorDao.create(director);

    }

    @Override
    public List<Director> getAll() {
        List<Director> directorsList = new ArrayList<>(directorDao.getAll());
        return directorsList;
    }

    @Override
    public Director get(int id) {
        return directorDao.getDirectorById(id);
    }

    @Override
    public Director update(Director director, int id) {
        return directorDao.update(director, id);
    }

    @Override
    public Director delete(int id) {
        return directorDao.delete(id);
    }
}
