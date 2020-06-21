package pl.umk.mat.danielsz.recipeapp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class OperationNotAllowedException extends RuntimeException {
    private HttpStatus httpStatus;
    private Date date;

    public OperationNotAllowedException(String message){
        super (message);
        this.date = new Date();
    }
}
