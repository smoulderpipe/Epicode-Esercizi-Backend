package entities;
import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "elementi_catalogo")
public abstract class ElementoCatalogo {
    @Id
    @GeneratedValue
    private UUID isbn;
    private String titolo;
    private Integer anno;
    private Integer pagine;


    public ElementoCatalogo(UUID isbn, String titolo, Integer anno, Integer pagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.anno = anno;
        this.pagine = pagine;

    }

    public ElementoCatalogo(){

    }

    public UUID getIsbn() {
        return this.isbn;
    }

    public void setIsbn(UUID isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return this.anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getPagine() {
        return this.pagine;
    }

    public void setPagine(Integer pagine) {
        this.pagine = pagine;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno: " + anno + ", Pagine: " + pagine;
    }

}
