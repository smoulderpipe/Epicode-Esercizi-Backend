package it.epicode;

public class DipendenteFullTime extends Dipendente {

    public DipendenteFullTime(String matricola, double stipendio, Dipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }

    @Override
    public double calcolaStipendio() {
        return (this.stipendio)/12;
    }
}