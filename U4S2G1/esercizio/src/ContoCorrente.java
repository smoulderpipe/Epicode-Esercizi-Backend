public class ContoCorrente {

    private double dispResidua;

    public double preleva(double importoPrelievo) throws BancaException {
        if (importoPrelievo > dispResidua) {
            throw new BancaException("Importo superiore alla disponibilità.");
        }
        dispResidua -= importoPrelievo;
        return dispResidua;
    }

    public double getDispResidua(){
        return this.dispResidua;
    }

    public void setDispResidua(int nuovaDisp){
        dispResidua = nuovaDisp;
    }

}
