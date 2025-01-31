package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> index() {
        List<AuthorDTO> authors = authorService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(authors.size()))
                .body(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> show(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.create(authorCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@RequestBody @Valid AuthorUpdateDTO authorUpdateDTO,
                                            @PathVariable long id) {
        return ResponseEntity.ok()
                .body(authorService.update(authorUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        authorService.deleteById(id);
    }
    // END
}
