package it.epicode.blogWebService.models;
import lombok.Data;

@Data
public class Post {
    private int id;
    private static int contatore;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    public Post(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura) {
        Post.contatore++;
        this.id = contatore;
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
    }
}
