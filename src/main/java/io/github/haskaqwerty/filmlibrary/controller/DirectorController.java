package io.github.haskaqwerty.filmlibrary.controller;

import io.github.haskaqwerty.filmlibrary.pojo.Director;
import io.github.haskaqwerty.filmlibrary.service.DirectorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DirectorController {

        private final DirectorServiceImpl directorService;

        @PostMapping(value = "/directors")
        public ResponseEntity<Director> createDirector(@RequestBody Director director){
            final Director created = directorService.create(director);
            return created != null
                    ? new ResponseEntity<>(created,HttpStatus.CREATED)
                    : new ResponseEntity(HttpStatus.NOT_MODIFIED);

        }

        @GetMapping(value = "/directors")
        public ResponseEntity<List<Director>> getAllDirectors(){
            final List<Director> directors = directorService.getAll();
            return directors != null && !directors.isEmpty()
                    ? new ResponseEntity<>(directors, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping(value = "/directors/{id}")
        public ResponseEntity<Director> getDirectorById(@PathVariable(name="id") Integer id){
            final Director director = directorService.get(id);
            return director != null
                    ? new ResponseEntity<>(director, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping(value = "/directors/{id}")
        public ResponseEntity<Director> updateDirector(@PathVariable(name = "id") Integer id, @RequestBody Director director){
            final Director updated = directorService.update(director,id);
            return updated != null
                    ? new ResponseEntity<>(updated,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        @DeleteMapping(value = "/directors/{id}")
        public ResponseEntity<Director> deleteDirector (@PathVariable(name = "id") Integer id){
            final Director deleted = directorService.delete(id);
            return deleted != null
                    ? new ResponseEntity<>(deleted, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


