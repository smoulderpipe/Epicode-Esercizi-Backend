import it.epicode.Variabili;
import java.util.Scanner;
import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Crea un'istanza della classe Variabili
        Variabili variabili = new Variabili();

        // Chiama i metodi della classe Variabili
        int risultatoMoltiplicazione = variabili.moltiplica(10, 5);
        String risultatoConcatenazione = variabili.concatena("ciao", 8);
        String[] nuovoArray = variabili.inserisciInArray(new String[]{"lunedì", "martedì", "mercoledì", "giovedì", "venerdì"}, "sabato");
        String[] treStringhe = Variabili.inserisciTreStringhe();

        // Stampa i risultati
        System.out.println("Risultato moltiplicazione: " + risultatoMoltiplicazione);
        System.out.println("Risultato concatenazione: " + risultatoConcatenazione);
        System.out.println("Risultato inserisciInArray: ");
        for (String elemento : nuovoArray) {
            System.out.print(elemento + ", ");
        };
        String concatenazioneOrdine = treStringhe[0] + " " + treStringhe[1] + " " + treStringhe[2];
        System.out.println("Concatenazione in ordine: " + concatenazioneOrdine);
        String concatenazioneInverso = treStringhe[2] + " " + treStringhe[1] + " " + treStringhe[0];
        System.out.println("Concatenazione in ordine inverso: " + concatenazioneInverso);

        System.out.print("Inserisci la base del rettangolo: ");
        double baseRettangolo = scanner.nextDouble();

        System.out.print("Inserisci l'altezza del rettangolo: ");
        double altezzaRettangolo = scanner.nextDouble();

        double perimetroRettangolo = Variabili.perimetroRettangolo(baseRettangolo, altezzaRettangolo);
        System.out.println("Perimetro del rettangolo: " + perimetroRettangolo);

        System.out.print("Inserisci un numero intero: ");
        int numero = scanner.nextInt();

        int risultatoPariDispari = Variabili.pariDispari(numero);
        if (risultatoPariDispari == 0) {
            System.out.println(numero + " è pari");
        } else {
            System.out.println(numero + " è dispari");
        }

        System.out.print("Inserisci il lato A del triangolo: ");
        double latoATriangolo = scanner.nextDouble();

        System.out.print("Inserisci il lato B del triangolo: ");
        double latoBTriangolo = scanner.nextDouble();

        System.out.print("Inserisci il lato C del triangolo: ");
        double latoCTriangolo = scanner.nextDouble();

        double areaTriangolo = Variabili.perimetroTriangolo(latoATriangolo, latoBTriangolo, latoCTriangolo);
        System.out.println("Area del triangolo: " + areaTriangolo);
    }
}