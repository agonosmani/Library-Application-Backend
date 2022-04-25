package mk.ukim.finki.lab2.service.impl;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.dto.BookDto;
import mk.ukim.finki.lab2.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.lab2.model.exceptions.BookNotFoundException;
import mk.ukim.finki.lab2.model.exceptions.NoMoreAvailableBooksException;
import mk.ukim.finki.lab2.repository.AuthorRepository;
import mk.ukim.finki.lab2.repository.BookRepository;
import mk.ukim.finki.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(book.getAvailableCopies() <= 0)
            throw new NoMoreAvailableBooksException(id);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
