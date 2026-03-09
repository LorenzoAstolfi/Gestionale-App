package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Impiegato.findAll", query="SELECT i FROM Impiegato i")
public class Impiegato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int matricola;

	private String codicefiscale;

	private String cognome;

	private String nome;

	//bi-directional many-to-one association to Storico
	@OneToMany(mappedBy="impiegato")
	private List<Storico> storicos;

	public Impiegato() {
	}

	public int getMatricola() {
		return this.matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCodicefiscale() {
		return this.codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Storico> getStoricos() {
		return this.storicos;
	}

	public void setStoricos(List<Storico> storicos) {
		this.storicos = storicos;
	}

	public Storico addStorico(Storico storico) {
		getStoricos().add(storico);
		storico.setImpiegato(this);

		return storico;
	}

	public Storico removeStorico(Storico storico) {
		getStoricos().remove(storico);
		storico.setImpiegato(null);

		return storico;
	}

}