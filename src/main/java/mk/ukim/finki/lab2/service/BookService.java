package mk.ukim.finki.lab2.service;

import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> update(Long id, BookDto bookDto);

    Optional<Book> markAsTaken(Long id);
}
