import adapter.DataSource;
import adapter.Info;
import adapter.InfoAdapter;
import adapter.UserData;
import composite.Libro;
import composite.Pagina;
import composite.Sezione;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        //esercizio adapter


        Info info = new Info();
        info.setNome("Mario");
        info.setCognome("Rossi");

        // Impostare una data di nascita
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.JANUARY, 1);
        info.DataDiNascita(calendar.getTime());

        // Creare l'adapter e passare l'istanza di Info
        DataSource adapter = new InfoAdapter(info);

        // Creare un'istanza di UserData e utilizzare l'adapter
        UserData userData = new UserData();
        userData.getData(adapter);

        // Verificare i risultati
        System.out.println("Nome Completo: " + userData.getNomeCompleto());
        System.out.println("Età: " + userData.getEta());




        //esercizio composite



        Pagina page1 = new Pagina(1);
        Pagina page2 = new Pagina(2);
        Pagina page3 = new Pagina(3);
        Pagina page4 = new Pagina(4);

        // Creare sezioni
        Sezione section1 = new Sezione("Chapter 1");
        section1.addComponent(page1);
        section1.addComponent(page2);

        Sezione section2 = new Sezione("Chapter 2");
        section2.addComponent(page3);
        section2.addComponent(page4);

        // Creare il libro
        Libro book = new Libro(29.99);
        book.addComponent(section1);
        book.addComponent(section2);
        book.addAuthor("Author One");
        book.addAuthor("Author Two");

        // Stampare il libro
        book.print();

        // Stampare il numero totale di pagine
        System.out.println("Total number of pages: " + book.getNumberOfPages());

        // Stampare gli autori del libro
        System.out.println("Authors: " + book.getAuthors());

        // Stampare il prezzo del libro
        System.out.println("Price: " + book.getPrice());


    }
}
