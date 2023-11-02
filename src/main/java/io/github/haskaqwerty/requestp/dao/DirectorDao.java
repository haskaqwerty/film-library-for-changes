package io.github.haskaqwerty.requestp.dao;

import io.github.haskaqwerty.requestp.pojo.Director;

import java.util.List;

public interface DirectorDao {
    Director getDirectorById(Integer id);
    List<Director> getAll();
    Director create(Director director);
    Director update(Director director, Integer id);
    Director delete(Integer id);

}
