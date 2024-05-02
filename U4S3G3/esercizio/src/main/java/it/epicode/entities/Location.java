package it.epicode.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private String nome;
    private String citta;

    public Location(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    public Location(){

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return this.citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Location{" +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
