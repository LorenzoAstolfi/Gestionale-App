package dao;

import java.util.List;
import model.Storico;

public interface StoricoDao {
	public Storico ricercaPerId(int id);

	public void inserisci(Storico i);

	public boolean aggiorna(Storico st2);

	public boolean elimina(int id);
	
	public List<Storico> ricercaPerMatricola(int matricola);
}
