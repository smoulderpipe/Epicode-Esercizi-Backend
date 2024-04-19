package it.epicode;

import javax.swing.text.Element;

public class Main {
    public static void main(String[] args) {

        ElementoMultimediale audio1 = new RegistrazioneAudio("Canzone", 3, 10);
        ElementoMultimediale video1 = new Video("Filmone", 4, 7, 5);
        ElementoMultimediale img1 = new Immagine("NicePic", 6);
        ElementoMultimediale img2 = new Immagine("NicePic2", 3);
        ElementoMultimediale video2 = new Video("Filmone2", 3, 4, 10);

        ElementoMultimediale[] elementiMultimediali = {
                audio1,
                video1,
                img1,
                img2,
                video2
        };

        for (ElementoMultimediale elemento : elementiMultimediali) {
            elemento.esegui();
        }

    }
}