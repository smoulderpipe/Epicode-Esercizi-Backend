package it.epicode.blogWebService.models;
import lombok.Data;

//in questo caso è uguale a CreatePostRequestBody, ma ne ho creati 2 per ricordarmi che ci sono casi in cui sono diversi
//(es. se voglio impedire la modifica di un attributo in fase di editPost)
@Data
public class EditPostRequestBody {
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
}
