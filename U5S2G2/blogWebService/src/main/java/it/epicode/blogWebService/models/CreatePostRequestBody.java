package it.epicode.blogWebService.models;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CreatePostRequestBody {
    @NonNull
    private String categoria;

    @NonNull
    private String titolo;

    @NonNull
    private String cover;

    @NonNull
    private String contenuto;

    @NonNull
    private int tempoDiLettura;
}
