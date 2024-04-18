package it.epicode;

public abstract class Dipendente {

    protected String matricola;
    protected double stipendio;
    protected Dipartimento dipartimento;

    public Dipendente(String matricola, double stipendio, Dipartimento dipartimento) {
        this.matricola = matricola;
        this.stipendio = stipendio;
        this.dipartimento = new Dipartimento(dipartimento.getNomeDipartimento());
    }

    public String getMatricola(){
        return this.matricola;
    }

    public double getStipendio(){
        return this.stipendio;
    }

    public Dipartimento getDipartimento(){
        return this.dipartimento;
    }

    public void setDipartimento(Dipartimento nuovoDipartimento) {
        this.dipartimento.setNomeDipartimento(nuovoDipartimento.getNomeDipartimento());
    }

    abstract public double calcolaStipendio();

}
