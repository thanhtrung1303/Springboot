package vn.techmaster.color.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class HandleException  {
    // Xy ly cho turong hop badrequest
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(BadRequestException e) {
        //log loi o day
        //log loi ra file
        log.error("loi roi fix di");
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    //xu ly cho truong hop NotFound
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    //XU ly truong hop con lai
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception e) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
