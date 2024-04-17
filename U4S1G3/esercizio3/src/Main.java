import it.epicode.Sim;

public class Main {
    public static void main(String[] args) {
        Sim istanzaSim = new Sim(3334455666L);

        for (int i = 0; i < 10; i++) {
            int durataMinuti = (int) (Math.random() * 100); // Durata casuale tra 0 e 100 minuti
            int numeroChiamato = (int) (Math.random() * 1000000000); // Numero casuale a 10 cifre
            istanzaSim.aggiungiChiamata(durataMinuti, numeroChiamato);
        }
        istanzaSim.stampaDatiSim();
    }
}