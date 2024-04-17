import it.epicode.Contatore;

public class Main {
    public static void main(String[] args) {

        Contatore contatore1 = new Contatore();
        Contatore contatore2 = new Contatore();
        Contatore contatore3 = new Contatore();

        System.out.println("Conteggio totale delle istanze: " + Contatore.getConteggioIstanze());

    }
}