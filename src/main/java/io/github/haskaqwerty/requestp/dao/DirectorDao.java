package io.github.haskaqwerty.filmlibrary.dao;

import io.github.haskaqwerty.filmlibrary.pojo.Director;

import java.util.List;

public interface DirectorDao {
    Director getDirectorById(int id);
    List<Director> getAll();
    boolean create(Director director);
    boolean update(Director director,int id);
    boolean delete(int id);

}
