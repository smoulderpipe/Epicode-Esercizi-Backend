package entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "libri")
@PrimaryKeyJoinColumn(name = "isbn")
public class Libro extends ElementoCatalogo {

    private String autore;
    private String genere;

    public Libro(UUID isbn, String titolo, Integer anno, Integer pagine, String autore, String genere) {
        super(isbn, titolo, anno, pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro (){

    }

    public String getAutore() {
        return this.autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return this.genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
    }

}