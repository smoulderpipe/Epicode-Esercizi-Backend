public class Periodicita {

    private String periodicita;


    public Periodicita(String periodicita) {
        String periodicitaLower = periodicita.toLowerCase();
        if (
                (!periodicitaLower.equals("settimanale")) &&
                        (!periodicitaLower.equals("mensile")) &&
                        (!periodicitaLower.equals("semestrale"))
        ) {
            throw new IllegalArgumentException("Periodicità non valida.");
        }
        this.periodicita = periodicitaLower;
    }

    public String getPeriodicita() {
        return this.periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
    }
}
