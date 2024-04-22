import java.util.Random;
import java.util.Scanner;

public class Esercizio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numeriCasuali = new int[5];
        Random random = new Random();

        for (int i = 0; i < numeriCasuali.length; i++) {

            int numeroCasuale = random.nextInt(10) + 1;
            numeriCasuali[i] = numeroCasuale;
                System.out.println(numeroCasuale);

        }

        boolean inputValido = false;
        while(!inputValido) {
            try {
                System.out.print("Scegli un numero: ");
                String nuovoNumeroStr = scanner.nextLine();
                int nuovoNumero = Integer.parseInt(nuovoNumeroStr);

                if (nuovoNumero == 0) {
                    throw new IllegalArgumentException("Il numero non può essere zero.");
                }

                System.out.print("In quale posizione vuoi inserirlo? ");
                String posizioneStr = scanner.nextLine();
                int posizione = Integer.parseInt(posizioneStr);

                if(posizione < 0 || posizione > numeriCasuali.length) {
                    throw new IllegalArgumentException("L'indice non può essere < 0 o > 5.");
                }

                numeriCasuali[posizione] = nuovoNumero;
                for (int numero : numeriCasuali) {
                    System.out.println(numero);
                }

                inputValido = true;

            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci un numero valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }


        }
        scanner.close();

    }

}