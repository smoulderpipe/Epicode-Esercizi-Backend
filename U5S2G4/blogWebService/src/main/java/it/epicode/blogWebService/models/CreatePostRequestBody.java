package it.epicode.blogWebService.models;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class CreatePostRequestBody {
    @NotEmpty
    private String categoria;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String titolo;

    @NotEmpty
    private String contenuto;

    @NotEmpty
    private int tempoDiLettura;

    @NotEmpty
    private UUID autoreId;
}
