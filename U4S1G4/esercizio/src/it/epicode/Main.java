package it.epicode;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Dipartimento x = new Dipartimento("AMMINISTRAZIONE");
        System.out.println(x.getNomeDipartimento());

        Dirigente boss = new Dirigente("235324", 25000, new Dipartimento("VENDITE"));
        DipendentePartTime sfaticato = new DipendentePartTime("37532", 10000, x);
        DipendenteFullTime workaholic = new DipendenteFullTime("374afa", 20000, new Dipartimento("PRODUZIONE"));

        Dipendente[] dipendenti = {
                boss,
                sfaticato,
                workaholic
        };
        double stipendiTotali = 0;

        for (int i = 0; i < dipendenti.length; i++) {
            stipendiTotali += dipendenti[i].calcolaStipendio();
        }
        System.out.println(stipendiTotali);
    }
}