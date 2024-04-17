package it.epicode;

public class Contatore {

    // Variabile statica per il conteggio delle istanze
    private static int conteggioIstanze = 0;

    // Costruttore che incrementa il conteggio delle istanze e stampa un messaggio
    public Contatore() {
        conteggioIstanze++;
        System.out.println("Creata nuova istanza! Numero di istanze totali: " + conteggioIstanze);
    }

    // Metodo per ottenere il valore del conteggio delle istanze
    public static int getConteggioIstanze() {
        return conteggioIstanze;
    }
}
