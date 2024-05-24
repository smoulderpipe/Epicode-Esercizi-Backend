package it.epicode.blogWebService.models;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
public class EditAutoreRequestBody {
    @Size(min = 1, max = 30)
    private String nome;

    @Size(min = 1, max = 30)
    private String cognome;

    @Email
    private String email;

    private LocalDate dataDiNascita;

    @URL
    private String avatar;
}
