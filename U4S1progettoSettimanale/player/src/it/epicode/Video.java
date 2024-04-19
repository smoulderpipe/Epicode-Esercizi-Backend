package it.epicode;

public class Video extends ElementoMultimediale implements CanItPlay{
    private int durata;
    private int volume;
    private int luminosita;

    public Video(String titolo, int durata, int volume, int luminosita) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
        this.luminosita = luminosita;
    }

    public int getDurata() {
        return this.durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getVolume() {
        return this.volume;
    }

    public void alzaVolume(int incrementoVolume) {
        this.volume += incrementoVolume;
    }

    public void abbassaVolume(int decrementoVolume) {
        this.volume -= decrementoVolume;
    }

    public int getLuminosita() {
        return this.luminosita;
    }

    public void alzaLum(int incrementoLum) {
        this.luminosita += incrementoLum;
    }

    public void abbassaLum(int decrementoLum) {
        this.luminosita -= decrementoLum;
    }

    @Override
    public void play() {
        for (int i = 0; i < this.durata; i++) {
            String titoloCorrente = this.getTitolo();
            for (int j = 0; j < this.volume; j++) {
                titoloCorrente += "!";
            }
            for (int k = 0; k < this.luminosita; k++) {
                titoloCorrente += "*";
            }
            System.out.println(titoloCorrente);
        }
    }

    @Override
    public void esegui() {
        this.play();
    }
}