package epicode;

import epicode.dao.StudenteDao;
import epicode.entity.Studente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode_jpa");
        EntityManager em = emf.createEntityManager();

        StudenteDao dao = new StudenteDao(em);

        Studente s1 = new Studente();
        s1.setNome("Rino");
        s1.setCognome("De Martino");
        s1.setIndirizzo("Via dei Mille");
        s1.setDataNascita(LocalDate.of(2000, 8, 6));

        // salvataggio di uno studente senza matricola(generata automaticamente da jpa)
        dao.save(s1);

        //estrazione dal db di uno studente con una determinata matricola
//        Studente s2 = dao.getById(1);
//        System.out.println(s2);
//
//        s2.setIndirizzo("Via dei Greci");


        //update di un record
//        dao.save(s2);

        dao.delete(s1);
    }
}
