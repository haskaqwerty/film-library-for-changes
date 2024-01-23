package io.github.haskaqwerty.filmlibrary.controller;

import io.github.haskaqwerty.filmlibrary.pojo.Genre;
import io.github.haskaqwerty.filmlibrary.service.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

        private final GenreServiceImpl genreService;

        @PostMapping(value = "/genres")
        public ResponseEntity createGenre(@RequestBody Genre genre){
            return genreService.create(genre)
                    ? new ResponseEntity<>(HttpStatus.CREATED)
                    : new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }

        @GetMapping(value = "/genres")
        public ResponseEntity<List<Genre>> getAllGenres(){
            final List<Genre> genreList = genreService.getAll();
            return genreList != null && !genreList.isEmpty()
                    ? new ResponseEntity<>(genreList, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping(value = "/genres/{id}")
        public ResponseEntity<Genre> getGenreById(@PathVariable(name="id") Integer id){
            final Genre genre = genreService.get(id);
            return genre != null
                    ? new ResponseEntity<>(genre, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping(value = "/genres/{id}")
        public ResponseEntity updateGenre(@PathVariable(name = "id") Integer id, @RequestBody Genre genre){
            return genreService.update(genre,id)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        @DeleteMapping(value = "/genres/{id}")
        public ResponseEntity deleteGenre (@PathVariable(name = "id") Integer id){
            return genreService.delete(id)
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


