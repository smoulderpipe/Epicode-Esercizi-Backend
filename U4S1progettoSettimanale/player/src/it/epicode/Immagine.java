package it.epicode;

public class Immagine extends ElementoMultimediale {
    private int luminosita;

    public Immagine(String titolo, int luminosita) {
        super(titolo);
        this.luminosita = luminosita;
    }

    public int getLuminosita(){
        return this.luminosita;
    }

    public void alzaLum(int incrementoLum){
        this.luminosita += incrementoLum;
    }

    public void abbassaLum(int decrementoLum){
        this.luminosita -= decrementoLum;
    }

    public void show(){
        String titoloCorrente = this.getTitolo();
        for(int i = 0; i < this.luminosita; i++) {
           titoloCorrente += "*";
        }
        System.out.println(titoloCorrente);
    }

    @Override
    public void esegui() {
        this.show();
    }
}
