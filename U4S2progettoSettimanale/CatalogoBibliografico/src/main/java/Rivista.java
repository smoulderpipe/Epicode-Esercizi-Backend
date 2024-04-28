public class Rivista extends ElementoCatalogo {


    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, Integer anno, Integer pagine, Periodicita periodicita) {
        super(isbn, titolo, anno, pagine, "Rivista");
        this.periodicita = new Periodicita(periodicita.getPeriodicita());
    }


    public Periodicita getPeriodicita() {
        return this.periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    public String toString() {
        return super.toString() + ", Periodicita: " + periodicita.getPeriodicita();
    }

}

