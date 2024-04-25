package clearsolution.pytaichuk.test.controller.exceptionHandler;

import clearsolution.pytaichuk.test.util.exception.FoundException;
import clearsolution.pytaichuk.test.util.exception.NotFoundException;
import clearsolution.pytaichuk.test.util.exception.ResponseException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<ResponseException> personExceptionHandler(ValidationException validationException){
        ResponseException responseException = new ResponseException(
                validationException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ResponseException> personExceptionHandler(NotFoundException notFoundException){
        ResponseException responseException = new ResponseException(
                notFoundException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FoundException.class)
    private ResponseEntity<ResponseException> personExceptionHandler(FoundException foundException){
        ResponseException responseException = new ResponseException(
                foundException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }
}
