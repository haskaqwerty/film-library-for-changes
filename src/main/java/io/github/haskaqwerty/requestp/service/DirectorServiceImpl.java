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
    public boolean create(Director director) {
        boolean result = directorDao.create(director);
        return result;
    }

    @Override
    public List<Director> readAll() {
        List<Director> directorsList = new ArrayList<>();
        directorsList = directorDao.getAll();
        System.out.println(directorsList.toString());
        return directorsList;
    }

    @Override
    public Director read(int id) {
        Director director = new Director();
        director = directorDao.getDirectorById(id);
        System.out.println(director.toString());
        return director;
    }

    @Override
    public boolean update(Director director, int id) {
        boolean result = directorDao.update(director, id);
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = directorDao.delete(id);
        return result;
    }
}
