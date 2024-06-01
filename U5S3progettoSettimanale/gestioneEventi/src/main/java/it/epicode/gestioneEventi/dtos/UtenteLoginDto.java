package it.epicode.gestioneEventi.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteLoginDto {
    @NotBlank(message = "L'e-mail non può essere vuota o mancante o composta da soli spazi.")
    @Email
    private String email;

    @NotBlank(message = "La password non può essere vuota o mancante o composta da soli spazi.")
    private String password;
}
