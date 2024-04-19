package it.epicode;

public class RegistrazioneAudio extends ElementoMultimediale implements CanItPlay {
    private int durata;
    private int volume;

    public RegistrazioneAudio(String titolo, int durata, int volume) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
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

    @Override
    public void play() {
        for (int i = 0; i < this.durata; i++) {
            String titoloCorrente = this.getTitolo();
            for (int j = 0; j < this.volume; j++) {
                titoloCorrente += "!";
            }
            System.out.println(titoloCorrente);
        }
    }

    @Override
    public void esegui() {
        this.play();
    }
}
