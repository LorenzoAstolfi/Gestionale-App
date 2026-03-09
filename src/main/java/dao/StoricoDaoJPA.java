package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Impiegato;
import model.Storico;

public class StoricoDaoJPA implements StoricoDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public StoricoDaoJPA() {
		emf = Persistence.createEntityManagerFactory("Hib4PU");
		em = emf.createEntityManager();
	}
	
	@Override
	public Storico ricercaPerId(int id) {
		try {
			Query q1 = em.createQuery("FROM Storico s WHERE s.idStorico = :id", Storico.class);
			q1.setParameter("id", id);
			Storico res = (Storico) q1.getSingleResult();
			return res;
			
		}catch(NoResultException n) {
			return null;
		}
	}

	@Override
	public void inserisci(Storico i) {
		em.getTransaction().begin();
		
		em.persist(i);
		
		em.getTransaction().commit();
	}

	@Override
	public boolean aggiorna(Storico i) {
		em.getTransaction().begin();
		
		em.merge(i);
		
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public boolean elimina(int id) {
		em.getTransaction().begin();
		
		Storico s = em.find(Storico.class, id);
		em.remove(s);
		
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public List<Storico> ricercaPerMatricola(int matricola) {
		try {
			Query q1 = em.createQuery("FROM Storico s WHERE s.matricola = :matr", Storico.class);
			q1.setParameter("matr", matricola);
			List<Storico> res = q1.getResultList();
			return res;
			
		}catch(NoResultException n) {
			return null;
		}
	}
	
	
}
