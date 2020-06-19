package pl.umk.mat.danielsz.recipeapp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class NotFoundException extends RuntimeException {
    private HttpStatus httpStatus;
    private Date date;

    public NotFoundException(String message) {
        super(message);
        this.date = new Date();
    }

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.date = new Date();
    }
}
