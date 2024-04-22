
//public class Aritmetica {
//    public int divisione(int a, int b) throws DivisionePer0Exception{
//        int risultato = 0;
//
//        try{
//            risultato = a/b;
//        } catch (ArithmeticException e) {
//            System.out.println(("Divisione per 0 non ammessa"));
//        }
//        return risultato;
//    }
//}

public class Aritmetica {

public int divisione(int a, int b) throws DivisionePer0Exception{
        int risultato = 0;

    if(b==0) {
        throw new DivisionePer0Exception("Divisione per 0 non ammessa(2)");
    } else {
        risultato = a/b;
    }
        return risultato;
    }
}
