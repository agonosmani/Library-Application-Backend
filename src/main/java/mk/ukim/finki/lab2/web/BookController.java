package mk.ukim.finki.lab2.web;

import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;
import mk.ukim.finki.lab2.model.enumerations.Category;
import mk.ukim.finki.lab2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "https://agonlibrary.herokuapp.com")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id,
                                     @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        if(bookService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/markastaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        bookService.markAsTaken(id);
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/categories")
    public List<String> findAllCategories() {
        return Stream.of(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
    }

}
