package adapter;

public class UserData {
    private String nomeCompleto;
    private int eta;

    public void getData(DataSource ds){
        nomeCompleto = ds.getNomeCompleto();
        eta = ds.getEta();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
}
