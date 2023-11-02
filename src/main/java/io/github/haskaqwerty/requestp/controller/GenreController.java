package io.github.haskaqwerty.requestp.controller;

import io.github.haskaqwerty.requestp.pojo.Genre;
import io.github.haskaqwerty.requestp.service.GenreServiceImpl;
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
        public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
            final Genre created = genreService.create(genre);
            return created != null
                    ? new ResponseEntity<>(genre,HttpStatus.CREATED)
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
        public ResponseEntity<Genre> updateGenre(@PathVariable(name = "id") Integer id, @RequestBody Genre genre){
            final Genre updated = genreService.update(genre,id);
            return updated !=null
                    ? new ResponseEntity<>(updated,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }


        @DeleteMapping(value = "/genres/{id}")
        public ResponseEntity<Genre> deleteGenre (@PathVariable(name = "id") Integer id){
            final Genre deleted = genreService.delete(id);
            return deleted != null
                    ? new ResponseEntity<>(deleted, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }


