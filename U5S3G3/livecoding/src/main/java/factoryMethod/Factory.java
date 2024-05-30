package factoryMethod;

public class Factory {

    private static Animale animale;

    public static Animale getAnimale(Tipo tipo){
        switch (tipo){
            case CANE: animale = new Cane();
                break;
            case GATTO: animale = new Gatto();
                break;
            case COCCODRILLO: animale = new Coccodrillo();
                break;
        }
        return animale;
    }
}
