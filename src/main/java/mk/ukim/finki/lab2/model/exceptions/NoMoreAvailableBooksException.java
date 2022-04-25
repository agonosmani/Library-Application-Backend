package mk.ukim.finki.lab2.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoMoreAvailableBooksException extends RuntimeException {
    public NoMoreAvailableBooksException(Long id) {
        super(String.format("There are no more available copies from the book: %d", id));
    }
}
