package it.epicode.progettoSpring.bean;
import lombok.Data;
import java.util.List;

@Data
public class Aula {
    private String nome;
    private List<Studente> studenti;

}
