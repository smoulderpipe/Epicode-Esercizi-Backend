package it.epicode;

public class Rettangolo {

    private int base;
    private int altezza;

    public Rettangolo(int base, int altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    public int calcoloPerimetro() {
        return (base + altezza)*2;
    }

    public int calcoloArea() {
        return (base * altezza);
    }

    public void stampaRettangolo() {
        System.out.println("Perimetro rettangolo 'test': " + calcoloPerimetro() + " metri");
        System.out.println("Area rettangolo 'test': " + calcoloArea() + " metri quadrati");
    }

    public static void stampaDueRettangoli(Rettangolo rettangolo1, Rettangolo rettangolo2) {
        System.out.println("Perimetro rettangolo1: " + rettangolo1.calcoloPerimetro() + " metri");
        System.out.println("Area rettangolo1: " + rettangolo1.calcoloArea() + " metri quadrati");
        System.out.println("Perimetro rettangolo2: " + rettangolo2.calcoloPerimetro() + " metri");
        System.out.println("Area rettangolo2: " + rettangolo2.calcoloArea() + " metri quadrati");

        System.out.println("Somma dei perimetri: " + rettangolo1.calcoloPerimetro() + rettangolo2.calcoloPerimetro());
        System.out.println("Somma delle aree: " + rettangolo1.calcoloArea() + rettangolo2.calcoloArea());
    }
}
