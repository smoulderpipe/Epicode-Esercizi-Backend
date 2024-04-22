import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsaAritmetica{

    static Logger logger = LoggerFactory.getLogger("logger 1");

public static void main(String[] args) {
    logger.info("Sto lanciando l'applicazione");
    logger.trace("Sto lanciando l'applicazione (2)");
    Aritmetica aritmetica = new Aritmetica();

    try{
        System.out.println(aritmetica.divisione(5,0));
    }catch(ArithmeticException e){
        System.out.println("Divisione per 0 non ammessa (1)");
    } catch(DivisionePer0Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
    logger.info("Sto terminando  l'applicazione");
    System.out.println("programma terminato");
}
}