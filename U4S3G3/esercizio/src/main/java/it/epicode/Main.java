package it.epicode;
import it.epicode.dao.EventoDao;
import it.epicode.dao.LocationDao;
import it.epicode.dao.PartecipazioneDao;
import it.epicode.dao.PersonaDao;
import it.epicode.entities.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestione_eventi");
        EntityManager em = emf.createEntityManager();

        LocationDao locationDao = new LocationDao(em);

        Location roma = new Location();
        roma.setCitta("Roma");
        roma.setNome("Festival");
//        locationDao.save(roma);

        EventoDao dao = new EventoDao(em);

        Evento e1 = new Evento();
        e1.setLocation(roma);
        e1.setTitolo("Pasqua");
        e1.setDataEvento(LocalDate.of(2023, 4, 2));
        e1.setDescrizione("Descrizione test");
        e1.setTipoEvento(TipoEvento.PRIVATO);

//         salvataggio di un evento senza id(generata automaticamente da jpa)
        dao.save(e1);
//
//        e1.setTipoEvento(TipoEvento.PUBBLICO);
//        dao.save(e1);

//        Evento evento2 = dao.getById(3);
//        System.out.println(evento2);
//
//        dao.delete(evento2);

        PersonaDao personaDao = new PersonaDao(em);

        Persona p1 = new Persona();
        p1.setNome("Giuseppe");
        p1.setCognome("Verdi");
        p1.setEmail("test@gmail.com");
        p1.setDataDiNascita(LocalDate.of(2024,3,3));
        p1.setSesso(Sesso.M);

        personaDao.save(p1);

        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(em);

        Partecipazione partecipazione1 = new Partecipazione();
        partecipazione1.setEvento(e1);
        partecipazione1.setStatoPartecipazione(StatoPartecipazione.CONFERMATA);
        partecipazione1.setPersona(p1);

        partecipazioneDao.save(partecipazione1);

    }

}
