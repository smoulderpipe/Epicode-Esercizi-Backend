import java.util.InputMismatchException;
import java.util.Scanner;

public class Esercizio3 {

    public static void main(String[] args) {
        Scanner scanner3 = new Scanner(System.in);

        ContoCorrente conto1 = new ContoCorrente();
        conto1.setDispResidua(700);
        System.out.println("La disponibilità iniziale del conto è di " + conto1.getDispResidua() + " €");

        boolean isValid = false;

        while(!isValid) {
            try {
                System.out.print("Scegli un importo da prelevare: ");
                double prelievo = scanner3.nextDouble();
                System.out.println("La disponibilità attuale del conto è di " + conto1.preleva(prelievo) + " €");
                isValid = true;
            } catch (BancaException e) {
                System.out.println("Errore: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Inserire un numero valido.");
                scanner3.next();
            }
        }

        scanner3.close();
    }
}
