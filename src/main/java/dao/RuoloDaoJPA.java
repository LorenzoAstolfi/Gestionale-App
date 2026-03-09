package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Impiegato;
import model.Ruolo;

public class RuoloDaoJPA implements RuoloDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public RuoloDaoJPA() {
		emf = Persistence.createEntityManagerFactory("Hib4PU");
		em = emf.createEntityManager();
	}
	
	@Override
	public Ruolo ricercaPerId(int id) {
		try {
			Query q1 = em.createQuery("FROM Ruolo r WHERE r.idRuolo = :id", Ruolo.class);
			q1.setParameter("id", id);
			Ruolo res = (Ruolo) q1.getSingleResult();
			return res;
			
		}catch(NoResultException n) {
			return null;
		}
	}

	@Override
	public void inserisci(Ruolo i) {
		em.getTransaction().begin();
		
		em.persist(i);
		
		em.getTransaction().commit();
	}

	@Override
	public boolean aggiorna(Ruolo i) {
		em.getTransaction().begin();
		
		em.merge(i);
		
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public boolean elimina(int idRuolo) {
		em.getTransaction().begin();
		
		Ruolo r = em.find(Ruolo.class, idRuolo);
		em.remove(r);
		
		em.getTransaction().commit();
		
		return true;
	}

}
