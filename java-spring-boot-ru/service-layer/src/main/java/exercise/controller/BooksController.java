package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping
    public ResponseEntity<List<BookDTO>> index() {
        List<BookDTO> books = bookService.getAll();

        return ResponseEntity.ok()
                .header("X-Total_Count", String.valueOf(books.size()))
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> show(@PathVariable long id) {
        return ResponseEntity.ok().body(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookCreateDTO bookCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(bookCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@RequestBody @Valid BookUpdateDTO bookUpdateDTO,
                                          @PathVariable long id) {
        return ResponseEntity.ok()
                .body(bookService.update(bookUpdateDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        bookService.deleteById(id);
    }
    // END
}
