package it.epicode.gestionedispositivi.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DipendenteDto {

    @NotEmpty
    @Size(min= 1, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 1, max = 30)
    private String nome;

    @NotBlank
    @Email
    private String email;

    private MultipartFile avatar;
}
