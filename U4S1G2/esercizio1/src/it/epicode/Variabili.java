package it.epicode;

public class Variabili {

    public boolean stringaPariDispari(String string) {
        return string.length() % 2 == 0;
    }

    public boolean annoBisestile(int anno) {
        if (anno % 4 == 0) {
            if (anno % 100 != 0) {
                return true;
            } else if (anno % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String stampaInLettere(int numero) {
        String numeroInLettere = "";
        if (0 < numero && numero < 3) {
            switch (numero) {
                case 1:
                    numeroInLettere = "uno";
                    break;
                case 2:
                    numeroInLettere = "due";
            }
        } else {
            System.out.println("Il numero non è compreso tra 0 e 3");
        }
        return numeroInLettere;
    }

    public String[] caratteriIndividuali(String parolaDiPartenza) {
        String[] caratteriSeparati = null;

        do {
            if (parolaDiPartenza.isEmpty()) {
                System.out.println("Errore: La stringa è vuota");
            } else if (parolaDiPartenza.equals("q")) {
                System.out.println("Arrivederci!");
                break;
            } else {
                caratteriSeparati = parolaDiPartenza.split("");
                break;
            }
        } while (!parolaDiPartenza.isEmpty());

        return caratteriSeparati;
    }

    public int[] contoAllaRovescia(int numeroDaInserire){
        int[] numeriContoRovescia = new int[numeroDaInserire + 1];
        for (int i = numeroDaInserire; i >= 0; i--) {
            numeriContoRovescia[i] = i;
        }

        for (int i = numeroDaInserire; i >= 0; i--) {
            System.out.println(numeriContoRovescia[i] + " ");
        }
        return numeriContoRovescia;
    }
}

