package it.epicode;

public class DipendentePartTime extends Dipendente {

    public DipendentePartTime(String matricola, double stipendio, Dipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }

    @Override
    public double calcolaStipendio() {
        return (this.stipendio)/24;
    }
}
