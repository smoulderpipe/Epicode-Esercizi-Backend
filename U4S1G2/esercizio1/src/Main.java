import it.epicode.Variabili;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Variabili variabili = new Variabili();

        boolean pariOppureDispari = variabili.stringaPariDispari("ciao");
        System.out.println("Esercizio 1");
        System.out.println(pariOppureDispari);

        int annoDaAnalizzare = 2024;
        boolean bisestile = variabili.annoBisestile(annoDaAnalizzare);
        String messaggioAnno = "L'anno " + annoDaAnalizzare + " è ";
        messaggioAnno += bisestile ? "bisestile" : "non bisestile";
        System.out.println(messaggioAnno);

        int numeroDaAnalizzare = 1;
        String numeroLettere = variabili.stampaInLettere(numeroDaAnalizzare);
        System.out.println(numeroLettere);

        String stringaDaAnalizzare;
        do {
            System.out.print("Inserisci una stringa (o 'q' per terminare): ");
            stringaDaAnalizzare = scanner.nextLine();

            if (!stringaDaAnalizzare.equals("q")) {
                String[] caratteriSeparati = variabili.caratteriIndividuali(stringaDaAnalizzare);
                System.out.println("Stringa divisa in caratteri:");
                for (String carattere : caratteriSeparati) {
                    System.out.print(carattere + ", ");
                }
                System.out.println();
            }
        } while (!stringaDaAnalizzare.equals("q"));

        scanner.close();

        int numeroDaDare = 10;
        int[] contoRovescia = variabili.contoAllaRovescia(10);

    }
}