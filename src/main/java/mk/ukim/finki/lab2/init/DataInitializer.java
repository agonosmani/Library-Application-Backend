package mk.ukim.finki.lab2.init;

import mk.ukim.finki.lab2.model.Author;
import mk.ukim.finki.lab2.model.Book;
import mk.ukim.finki.lab2.model.Country;
import mk.ukim.finki.lab2.model.enumerations.Category;
import mk.ukim.finki.lab2.repository.AuthorRepository;
import mk.ukim.finki.lab2.repository.BookRepository;
import mk.ukim.finki.lab2.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country country1 = new Country("USA", "North America");
        Country country2 = new Country("England", "Europe");
        Country country3 = new Country("Russia", "Asia");
        Country country4 = new Country("Italy", "Europe");
        Country country5 = new Country("France", "Europe");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        countryRepository.save(country4);
        countryRepository.save(country5);

        Author author1 = new Author("William", "Shakespeare", country2);
        Author author2 = new Author("Ernest", "Hemingway", country1);
        Author author3 = new Author("Fyodor", "Dostoevski", country3);
        Author author4 = new Author("Dante", "Alighieri", country4);
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);

        Book book1 = new Book("Crime And Punishment", Category.NOVEL, author3, 5);
        Book book2 = new Book("The Gambler", Category.NOVEL, author3, 2);
        Book book3 = new Book("The Divine Comedy", Category.CLASSICS, author4, 7);
        Book book4 = new Book("Inferno", Category.THRILLER, author4, 3);
        Book book5 = new Book("The Old Man and the Sea", Category.FANTASY, author2, 2);
        Book book6 = new Book("Hamlet", Category.DRAMA, author1, 1);
        Book book7 = new Book("Macbeth", Category.CLASSICS, author1, 3);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
    }
}
