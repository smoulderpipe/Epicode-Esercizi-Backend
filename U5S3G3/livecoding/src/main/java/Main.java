import adapter.*;
import chainOfResponsability.*;
import composite.Libro;
import composite.Scatolo;
import factoryMethod.Animale;
import factoryMethod.Factory;
import factoryMethod.Tipo;
import singleton.CorsoEpicode;


public class Main {
    public static void main(String[] args) {
        //CorsoEpicode corsoEpicode1 = new CorsoEpicode("Back-end", LocalDate.now(), 36, "Roma");
        //CorsoEpicode corsoEpicode2 = new CorsoEpicode("Front-end", LocalDate.now()), 34, "Napoli");

        //esercizio singleton:
        CorsoEpicode corsoEpicode1 = CorsoEpicode.getInstance();
        CorsoEpicode corsoEpicode2 = CorsoEpicode.getInstance();
        System.out.println(corsoEpicode1.hashCode());
        System.out.println(corsoEpicode2.hashCode());

        //esercizio factory method:
        Animale animale1 = Factory.getAnimale(Tipo.CANE);
        Animale animale2 = Factory.getAnimale(Tipo.GATTO);
        Animale animale3 = Factory.getAnimale(Tipo.COCCODRILLO);

        animale1.verso();
        animale2.verso();
        animale3.verso();

        //esercizio adapter
        Quadrato quadrato = new Quadrato(8);
        Triangolo triangolo = new Triangolo(4, 7, 9);
        Rettangolo rettangolo = new Rettangolo(3, 7);
        FiguraGeometricaAdapter rettangoloAdapter = new FiguraGeometricaAdapter(rettangolo);

        CalcolatoreFigure calcolatoreFigure = new CalcolatoreFigure();
        calcolatoreFigure.add(quadrato);
        calcolatoreFigure.add(triangolo);
        calcolatoreFigure.add(rettangoloAdapter);

        System.out.println(calcolatoreFigure.perimetroTotale() + " e " + calcolatoreFigure.areaTotale());

        //esercizio composite

        Libro l1 = new Libro("l1", 5, 200);
        Libro l2 = new Libro("l2", 6, 300);
        Libro l3 = new Libro("l3", 7, 100);
        Libro l4 = new Libro("l4", 8, 500);
        Libro l5 = new Libro("l5", 9, 200);
        Libro l6 = new Libro("l6", 10, 700);
        Libro l7 = new Libro("l7", 11, 250);
        Libro l8 = new Libro("l8", 12, 200);
        Libro l9 = new Libro("l9", 13, 400);
        Libro l10 = new Libro("l10", 14, 100);

        Scatolo scatolo1 = new Scatolo("scatolo1");
        Scatolo scatolo2 = new Scatolo("scatolo2");
        Scatolo scatolo3 = new Scatolo("scatolo3");
        Scatolo scatolo4 = new Scatolo("scatolo4");

        scatolo4.add(l1);
        scatolo4.add(l2);
        scatolo4.add(l3);

        scatolo3.add(l4);
        scatolo3.add(l5);
        scatolo3.add(l6);

        scatolo2.add(l7);
        scatolo2.add(l8);
        scatolo2.add(l9);

        scatolo1.add(l10);
        scatolo1.add(scatolo2);
        scatolo1.add(scatolo3);
        scatolo1.add(scatolo4);

        System.out.println(scatolo1.getPrezzo());
        System.out.println(scatolo1.getPeso());

        //esercizio chainOfResponsability
        ContoBancario contoBancario = new ContoBancario(123, 1000, 479, 479);

        Erogatore200 erogatore200 = new Erogatore200();
        Erogatore100 erogatore100 = new Erogatore100();
        Erogatore50 erogatore50 = new Erogatore50();
        Erogatore20 erogatore20 = new Erogatore20();
        Erogatore10 erogatore10 = new Erogatore10();
        Erogatore5 erogatore5 = new Erogatore5();
        Erogatore2 erogatore2 = new Erogatore2();
        Erogatore1 erogatore1 = new Erogatore1();

        erogatore200.setErogatoreSuccessivo(erogatore100);
        erogatore100.setErogatoreSuccessivo(erogatore50);
        erogatore50.setErogatoreSuccessivo(erogatore20);
        erogatore20.setErogatoreSuccessivo(erogatore10);
        erogatore10.setErogatoreSuccessivo(erogatore5);
        erogatore5.setErogatoreSuccessivo(erogatore2);
        erogatore2.setErogatoreSuccessivo(erogatore1);

        erogatore200.eroga(contoBancario);

    }
}
