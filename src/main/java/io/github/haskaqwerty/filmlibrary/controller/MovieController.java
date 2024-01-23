package io.github.haskaqwerty.filmlibrary.controller;

import io.github.haskaqwerty.filmlibrary.pojo.Movie;
import io.github.haskaqwerty.filmlibrary.service.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;

    @PostMapping(value = "/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return movieService.create(movie)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity(HttpStatus.NOT_MODIFIED);

    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        final List<Movie> movies = movieService.getAll();
        return movies != null && !movies.isEmpty()
                ? new ResponseEntity<>(movies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(name = "id") Integer id) {
        final Movie movie = movieService.getById(id);
        return movie != null
                ? new ResponseEntity<>(movie, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/movies/{id}")
    public ResponseEntity updateMovie(@PathVariable(name = "id") Integer id, @RequestBody Movie movie) {
        return movieService.update(movie, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/movies/{id}")
    public ResponseEntity deleteMovie(@PathVariable(name = "id") Integer id) {
        return movieService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

