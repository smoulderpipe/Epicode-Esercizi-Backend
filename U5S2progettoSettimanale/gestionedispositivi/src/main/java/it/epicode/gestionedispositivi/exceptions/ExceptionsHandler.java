package it.epicode.gestionedispositivi.exceptions;
import it.epicode.gestionedispositivi.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DipendenteNonTrovatoException.class)
    public ResponseEntity<Object> DipendenteNonTrovatoHandler(DipendenteNonTrovatoException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.NOT_FOUND);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DispositivoNonTrovatoException.class)
    public ResponseEntity<Object> DispositivoNonTrovatoHandler(DispositivoNonTrovatoException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.NOT_FOUND);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> DispositivoNonTrovatoHandler(BadRequestException e){
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setErrorStatus(HttpStatus.BAD_REQUEST);
        error.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
