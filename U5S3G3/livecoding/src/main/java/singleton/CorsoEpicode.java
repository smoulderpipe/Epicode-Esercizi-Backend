package singleton;

import java.time.LocalDate;

public class CorsoEpicode {
    private String nome;
    private LocalDate dataInizio;
    private int numeroMaxPartecipanti;
    private String sede;

    //2) questa variabile conterrà l'oggetto CorsoEpicode che andremo a creare
    private static CorsoEpicode corsoEpicode;

    private CorsoEpicode(String nome, LocalDate dataInizio, int numeroMaxPartecipanti, String sede) {
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
        this.sede = sede;
    }

    //1) questo metodo dovrà restituire sempre lo stesso oggetto, per farlo bisogna creare la nuova variabile d'istanza "corsoEpicode"
    //entry point per la creazione del singolo corsoEpicode
    public static CorsoEpicode getInstance(){
        if(corsoEpicode==null){
            corsoEpicode = new CorsoEpicode("Back-end", LocalDate.now(), 35, "Roma");
        }
        return corsoEpicode;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
