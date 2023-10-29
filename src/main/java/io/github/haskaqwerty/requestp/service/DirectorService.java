package io.github.haskaqwerty.filmlibrary.service;

import io.github.haskaqwerty.filmlibrary.pojo.Director;

import java.util.List;

public interface DirectorService {
    boolean create(Director director);
    List<Director> readAll();
    Director read(int id);
    boolean update(Director director, int id);
    boolean delete(int id);


}
