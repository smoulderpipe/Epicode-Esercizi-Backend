package dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.ElementoCatalogo;
import entities.Libro;

import java.util.List;
import java.util.UUID;

public class ElementoCatalogoDao {

    private EntityManager em;

    public ElementoCatalogoDao(EntityManager em){
        this.em = em;
    }

    public void save (ElementoCatalogo elementoCatalogo) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(elementoCatalogo);
        et.commit();

    }

    public ElementoCatalogo getByISBN (UUID isbn) {
        ElementoCatalogo e = em.find(ElementoCatalogo.class, isbn);
        return e;
    }

    public void rimuoviPerISBN (UUID isbn) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
        em.remove(elemento);
        et.commit();
    }

    public List<ElementoCatalogo> findByAnno(int anno){
        Query query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.anno = :anno"
        );
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<Libro> findByAutore(String autore){
        Query query = em.createQuery(
                "SELECT e FROM Libro e WHERE LOWER(e.autore) = LOWER(:autore)");
        query.setParameter("autore", autore.toLowerCase());
        return query.getResultList();
    }

    public List<ElementoCatalogo> findByTitolo(String titolo){
        Query query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)");
        query.setParameter("titolo", "%" + titolo.toLowerCase() + "%");
        return query.getResultList();
    }

}
