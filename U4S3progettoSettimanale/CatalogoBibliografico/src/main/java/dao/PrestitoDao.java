package dao;
import entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {

    private EntityManager em;

    public PrestitoDao(EntityManager em){
        this.em = em;
    }
    public void save (Prestito prestito) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(prestito);
        et.commit();

    }

    public List<Prestito> findByNumeroTesseraUtente(int numeroTessera){
        Query query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera "
                        + "AND (p.dataRestituzioneEffettiva IS NULL OR p.dataRestituzioneEffettiva < :dataAttuale)");

        query.setParameter("numeroTessera", numeroTessera);
        query.setParameter("dataAttuale", LocalDate.now());
        return query.getResultList();
    }


    public List<Prestito> findScaduti(){
        Query query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista IS NOT NULL" +
                        " AND p.dataRestituzioneEffettiva IS NULL OR p.dataRestituzioneEffettiva > p.dataRestituzionePrevista" +
                        " AND p.dataRestituzionePrevista < CURRENT_DATE"
        );
        return query.getResultList();

    }
}
