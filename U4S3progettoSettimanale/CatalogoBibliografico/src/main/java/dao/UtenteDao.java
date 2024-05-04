package dao;

import entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDao {

    private EntityManager em;

    public UtenteDao(EntityManager em){
        this.em = em;
    }

    public void save (Utente utente){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(utente);
        et.commit();
    }
}
