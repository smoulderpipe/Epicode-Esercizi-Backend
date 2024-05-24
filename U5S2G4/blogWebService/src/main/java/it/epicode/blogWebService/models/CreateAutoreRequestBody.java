package it.epicode.blogWebService.models;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDate;

@Data
public class CreateAutoreRequestBody {
    @NotEmpty
    private String nome;

    @NotEmpty
    private String cognome;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private LocalDate dataDiNascita;

    @NotBlank
    @URL
    private String avatar;
}
