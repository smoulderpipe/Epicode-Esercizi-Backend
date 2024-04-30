package it.epicode;

import it.epicode.dao.EventoDao;
import it.epicode.entities.Evento;
import it.epicode.entities.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestione_eventi");
        EntityManager em = emf.createEntityManager();

        EventoDao dao = new EventoDao(em);

        Evento e1 = new Evento();
        e1.setTitolo("Pasqua");
        e1.setDataEvento(LocalDate.of(2023, 4, 2));
        e1.setDescrizione("Descrizione test");
        e1.setTipoEvento(TipoEvento.PRIVATO);

        // salvataggio di un evento senza id(generata automaticamente da jpa)
        dao.save(e1);

        e1.setTipoEvento(TipoEvento.PUBBLICO);
        dao.save(e1);

        Evento evento2 = dao.getById(1);
        System.out.println(evento2);

        dao.delete(evento2);

    }

}
