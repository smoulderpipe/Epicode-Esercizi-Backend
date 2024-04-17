import it.epicode.Rettangolo;

public class Main {
    public static void main(String[] args) {

     Rettangolo IstanzaRettangolo = new Rettangolo(3, 4);
     IstanzaRettangolo.stampaRettangolo();


     Rettangolo.stampaDueRettangoli(new Rettangolo(50, 60), new Rettangolo(30, 10));
    }
}