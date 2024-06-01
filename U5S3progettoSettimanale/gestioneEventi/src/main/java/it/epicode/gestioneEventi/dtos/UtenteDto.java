package it.epicode.gestioneEventi.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UtenteDto {

    @NotBlank
    @Size(min=1, max=15)
    private String username;

    @NotBlank
    @Size(min=1, max=20)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    @Size(min=1, max=40)
    private String nomeCompleto;

}
