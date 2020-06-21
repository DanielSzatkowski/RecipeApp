package pl.umk.mat.danielsz.recipeapp.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
        NotFoundException.class,
        OperationNotAllowedException.class
    })
    public ResponseEntity<Object> handle(Exception exception, WebRequest webRequest) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(exception instanceof NotFoundException){
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            return handleNotFoundException((NotFoundException) exception, httpHeaders, httpStatus, webRequest);
        } else if(exception instanceof OperationNotAllowedException) {
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
            return handleOperationNotAllowedException((OperationNotAllowedException) exception, httpHeaders, httpStatus, webRequest);
        } else {
            throw exception;
        }
    }


    //TODO: handle... merge to one method
    private ResponseEntity<Object> handleOperationNotAllowedException(OperationNotAllowedException exception, HttpHeaders httpHeaders,
          HttpStatus httpStatus, WebRequest webRequest) {

        ResponseError responseError = new ResponseError(httpStatus, exception.getMessage());

        return handleExceptionInternal(exception, responseError, httpHeaders, httpStatus, webRequest);
    }

    protected ResponseEntity<Object> handleNotFoundException(NotFoundException exception, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
        ResponseError responseError = new ResponseError(httpStatus, exception.getMessage());

        return handleExceptionInternal(exception, responseError, httpHeaders, httpStatus, webRequest);
    }
}
