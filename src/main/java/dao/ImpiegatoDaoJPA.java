package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Impiegato;

public class ImpiegatoDaoJPA implements ImpiegatoDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ImpiegatoDaoJPA() {
		emf = Persistence.createEntityManagerFactory("Hib4PU");
		em = emf.createEntityManager();
	}
	
	@Override
	public Impiegato ricercaPerCodiceFiscale(String codiceFiscale) {
		try {
			Query q1 = em.createQuery("FROM Impiegato i WHERE i.codicefiscale = :cf", Impiegato.class);
			q1.setParameter("cf", codiceFiscale);
			Impiegato res = (Impiegato) q1.getSingleResult();
			return res;
			
		}catch(NoResultException n) {
			return null;
		}
	}

	@Override
	public List<Impiegato> ricercaPerCognome(String cognome) {
		try {
			Query q1 = em.createQuery("FROM Impiegato i WHERE i.cognome = :cogn", Impiegato.class);
			q1.setParameter("cogn", cognome);
			List<Impiegato> res = q1.getResultList();
			return res;
			
		}catch(NoResultException n) {
			return null;
		}
	}

	@Override
	public void inserisci(Impiegato i) {
		em.getTransaction().begin();
		
		em.persist(i);
		
		em.getTransaction().commit();
	}

	@Override
	public boolean aggiorna(Impiegato imp2) {
		em.getTransaction().begin();
		
		em.merge(imp2);
		
		em.getTransaction().commit();
		
		return true;
	}

	@Override
	public boolean elimina(String codiceFiscale) {
		em.getTransaction().begin();
		
		Query q1 = em.createQuery("FROM Impiegato i WHERE i.codicefiscale = :cf", Impiegato.class);
		q1.setParameter("cf", codiceFiscale);
		Impiegato i = (Impiegato) q1.getSingleResult();
		
		if(i != null) {
			em.remove(i);
		} else {
			em.getTransaction().rollback();
			return false;
		}
		
		em.getTransaction().commit();
		
		return true;
	}

}
