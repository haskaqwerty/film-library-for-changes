package io.github.haskaqwerty.requestp.controller;

import io.github.haskaqwerty.requestp.pojo.Movie;
import io.github.haskaqwerty.requestp.service.MovieServiceImpl;
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
        final Movie created = movieService.create(movie);
        return created != null
                ? new ResponseEntity<>(created, HttpStatus.CREATED)
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
        final Movie movie = movieService.get(id);
        return movie != null
                ? new ResponseEntity<>(movie, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(name = "id") Integer id, @RequestBody Movie movie) {
        final Movie updated = movieService.update(movie, id);
        return updated != null
                ? new ResponseEntity<>(updated ,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable(name = "id") Integer id) {
        final Movie deleted = movieService.delete(id);
        return deleted != null
                ? new ResponseEntity<>(deleted, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

