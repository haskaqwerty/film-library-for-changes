package io.github.haskaqwerty.filmlibrary.controller;

import io.github.haskaqwerty.filmlibrary.pojo.Director;
import io.github.haskaqwerty.filmlibrary.pojo.Genre;
import io.github.haskaqwerty.filmlibrary.pojo.Movie;
import io.github.haskaqwerty.filmlibrary.service.DirectorServiceImpl;
import io.github.haskaqwerty.filmlibrary.service.GenreServiceImpl;
import io.github.haskaqwerty.filmlibrary.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
        private final MovieServiceImpl movieService;
        private final DirectorServiceImpl directorService;
        private final GenreServiceImpl genreService;

        @Autowired
        public Controller(MovieServiceImpl movieService, DirectorServiceImpl directorService, GenreServiceImpl genreService) {
            this.movieService = movieService;
            this.directorService = directorService;
            this.genreService = genreService;
        }

        @PostMapping(value = "/movies")
        public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
            final boolean created = movieService.create(movie);
            return created
                    ? new ResponseEntity<>(movie,HttpStatus.CREATED)
                    : new ResponseEntity(HttpStatus.NOT_MODIFIED);

        }

        @PostMapping(value = "/genres")
        public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
            final boolean created = genreService.create(genre);
            return created
                    ? new ResponseEntity<>(genre,HttpStatus.CREATED)
                    : new ResponseEntity(HttpStatus.NOT_MODIFIED);

        }
        @PostMapping(value = "/directors")
        public ResponseEntity<Director> createDirector(@RequestBody Director director){
            final boolean created = directorService.create(director);
            return created
                    ? new ResponseEntity<>(director,HttpStatus.CREATED)
                    : new ResponseEntity(HttpStatus.NOT_MODIFIED);

        }

        @GetMapping(value = "/movies")
        public ResponseEntity<List<Movie>> readAllMovies(){
            final List<Movie> movies = movieService.readAll();
            return movies != null && !movies.isEmpty()
                    ? new ResponseEntity<>(movies, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping(value = "/genres")
        public ResponseEntity<List<Genre>> readAllGenres(){
            final List<Genre> genreList = genreService.readAll();
            return genreList != null && !genreList.isEmpty()
                    ? new ResponseEntity<>(genreList, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping(value = "/directors")
        public ResponseEntity<List<Director>> readAllDirectors(){
            final List<Director> directors = directorService.readAll();
            return directors != null && !directors.isEmpty()
                    ? new ResponseEntity<>(directors, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping(value = "/movies/{id}")
        public ResponseEntity<Movie> readMovieById(@PathVariable(name="id") int id){
            final Movie movie = movieService.read(id);
            return movie != null
                    ? new ResponseEntity<>(movie, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping(value = "/directors/{id}")
        public ResponseEntity<Director> readDirectorById(@PathVariable(name="id") int id){
            final Director director = directorService.read(id);
            return director != null
                    ? new ResponseEntity<>(director, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping(value = "/genres/{id}")
        public ResponseEntity<Genre> readGenreById(@PathVariable(name="id") int id){
            final Genre genre = genreService.read(id);
            return genre != null
                    ? new ResponseEntity<>(genre, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PutMapping(value = "/movies/{id}")
        public ResponseEntity<Movie> updateMovie(@PathVariable(name = "id") int id, @RequestBody Movie movie){
            final boolean updated = movieService.update(movie,id);
            return updated

                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        @PutMapping(value = "/directors/{id}")
        public ResponseEntity<Director> updateDirector(@PathVariable(name = "id") int id, @RequestBody Director director){
            final boolean updated = directorService.update(director,id);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        @PutMapping(value = "/genres/{id}")
        public ResponseEntity<Movie> updateGenre(@PathVariable(name = "id") int id, @RequestBody Genre genre){
            final boolean updated = genreService.update(genre,id);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        @DeleteMapping(value = "/movies/{id}")
        public ResponseEntity<Movie> deleteMovie (@PathVariable(name = "id") int id){
            final boolean deleted = movieService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        @DeleteMapping(value = "/genres/{id}")
        public ResponseEntity<Genre> deleteGenre (@PathVariable(name = "id") int id){
            final boolean deleted = genreService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        @DeleteMapping(value = "/directors/{id}")
        public ResponseEntity<Director> deleteDirector (@PathVariable(name = "id") int id){
            final boolean deleted = directorService.delete(id);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


