package it.epicode;

public class Dipartimento {
    private String nomeDipartimento;

    public Dipartimento(String nomeDipartimento){
        if((!nomeDipartimento.equals("PRODUZIONE")) && (!nomeDipartimento.equals("AMMINISTRAZIONE")) && (!nomeDipartimento.equals("VENDITE"))) {
            throw new IllegalArgumentException("Nome dipartimento non valido.");
        }
        this.nomeDipartimento = nomeDipartimento;
    }

    public String getNomeDipartimento(){
        return this.nomeDipartimento;
    }

    public void setNomeDipartimento(String nomeDipartimento){
        if((!nomeDipartimento.equals("PRODUZIONE")) && (!nomeDipartimento.equals("AMMINISTRAZIONE")) && (!nomeDipartimento.equals("VENDITE"))) {
            throw new IllegalArgumentException("Nome dipartimento non valido.");
        }
        this.nomeDipartimento = nomeDipartimento;
    }
}
