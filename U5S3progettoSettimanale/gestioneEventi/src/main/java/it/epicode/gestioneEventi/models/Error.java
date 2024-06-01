package it.epicode.gestioneEventi.models;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class Error {
    private String message;
    private HttpStatus errorStatus;
    private LocalDateTime errorTime;
}
