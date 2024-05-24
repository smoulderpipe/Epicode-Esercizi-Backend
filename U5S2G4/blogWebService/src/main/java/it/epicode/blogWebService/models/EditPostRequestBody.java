package it.epicode.blogWebService.models;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;


//in questo caso è uguale a CreatePostRequestBody, ma ne ho creati 2 per ricordarmi che ci sono casi in cui sono diversi
//(es. se voglio impedire la modifica di un attributo in fase di editPost)
@Data
public class EditPostRequestBody {

    private String categoria;

    @Size(min = 1, max = 50)
    private String titolo;

    private String contenuto;

    @Min(1)
    @Max(60)
    private int tempoDiLettura;

    private UUID autoreId;
}
