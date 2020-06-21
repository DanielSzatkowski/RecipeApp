package pl.umk.mat.danielsz.recipeapp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class UnauthorizedAccessException extends RuntimeException {

    private HttpStatus httpStatus;
    private Date date;

    public UnauthorizedAccessException(String message) {
        super(message);
        this.date = new Date();
    }
}
