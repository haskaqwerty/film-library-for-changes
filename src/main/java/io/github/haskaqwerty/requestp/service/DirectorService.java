package io.github.haskaqwerty.requestp.service;

import io.github.haskaqwerty.requestp.pojo.Director;

import java.util.List;

public interface DirectorService {
    Director create(Director director);
    List<Director> getAll();
    Director get(int id);
    Director update(Director director, int id);
    Director delete(int id);


}
