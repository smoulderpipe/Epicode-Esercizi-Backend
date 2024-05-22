package it.epicode.blogWebService.models;
import lombok.Data;
import lombok.NonNull;
import java.time.LocalDate;

@Data
public class CreateAutoreRequestBody {
    @NonNull
    private String nome;

    @NonNull
    private String cognome;

    @NonNull
    private String email;

    @NonNull
    private LocalDate dataDiNascita;

    @NonNull
    private String avatar;
}
