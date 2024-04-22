import java.util.InputMismatchException;
import java.util.Scanner;

public class Esercizio2 {

    public static void main(String[] args) {

        Scanner scanner2 = new Scanner(System.in);

        boolean isValid = false;
        while(!isValid){
            try {
                System.out.print("Inserisci i km percorsi: ");
                double kmPercorsi = scanner2.nextDouble();
                System.out.print("Inserisci i litri di carburante consumati: ");
                double ltConsumati = scanner2.nextDouble();
                if(ltConsumati == 0) {
                    throw new IllegalArgumentException("Il numero deve essere diverso da zero.");
                }
                double kmPerLt = kmPercorsi / ltConsumati;
                System.out.println("Sono stati percorsi " + kmPerLt + "km per litro di carburante.");

                isValid = true;
            }

            catch(InputMismatchException e){
                System.out.println("Errore: Inserisci un numero valido.");
                scanner2.next();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }



    }
}
