package pl.umk.mat.danielsz.recipeapp.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ResponseError {
    private HttpStatus httpStatus;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;

    public ResponseError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.date = new Date();
    }
}
