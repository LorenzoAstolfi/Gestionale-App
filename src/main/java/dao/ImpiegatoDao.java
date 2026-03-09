package dao;

import java.util.ArrayList;
import java.util.List;

import model.Impiegato;

public interface ImpiegatoDao {

	public Impiegato ricercaPerCodiceFiscale(String codiceFiscale);
	
	public List<Impiegato> ricercaPerCognome(String cognome);

	public void inserisci(Impiegato i);

	public boolean aggiorna(Impiegato imp2);

	public boolean elimina(String codiceFiscale);

}
