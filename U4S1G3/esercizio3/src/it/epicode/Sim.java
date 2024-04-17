package it.epicode;

public class Sim {
    private long numeroDiTelefono;
    private int creditoResiduo;
    private ListaChiamate[] listaChiamate = new ListaChiamate[5];
    private int numChiamate = 0;

    private class ListaChiamate {
        private int durataMinuti;
        private int numeroChiamato;

        public ListaChiamate(int durataMinuti, int numeroChiamato) {
            this.durataMinuti = durataMinuti;
            this.numeroChiamato = numeroChiamato;
        }
    }

    public Sim(long numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
        System.out.println("Credito residuo: " + creditoResiduo);
        System.out.println("Ultime chiamate effettuate:");

    }
    public void aggiungiChiamata(int durataMinuti, int numeroChiamato) {
        if (numChiamate < listaChiamate.length) {
            listaChiamate[numChiamate++] = new ListaChiamate(durataMinuti, numeroChiamato);
        }
    }


    public void stampaDatiSim() {
        System.out.println("Numero di telefono: " + numeroDiTelefono);
        System.out.println("Credito residuo: " + creditoResiduo);
        System.out.println("Ultime chiamate effettuate:");

        for (int i = 0; i < numChiamate; i++) {
            if (listaChiamate[i] != null) {
                System.out.println("Durata: " + listaChiamate[i].durataMinuti + " minuti, Numero Chiamato: " + listaChiamate[i].numeroChiamato);
            }
        }
    }



}
