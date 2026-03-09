package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ruolo database table.
 * 
 */
@Entity
@NamedQuery(name="Ruolo.findAll", query="SELECT r FROM Ruolo r")
public class Ruolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRuolo;

	private String descrizione;

	//bi-directional many-to-one association to Storico
	@OneToMany(mappedBy="ruolo")
	private List<Storico> storicos;

	public Ruolo() {
	}

	public int getIdRuolo() {
		return this.idRuolo;
	}

	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Storico> getStoricos() {
		return this.storicos;
	}

	public void setStoricos(List<Storico> storicos) {
		this.storicos = storicos;
	}

	public Storico addStorico(Storico storico) {
		getStoricos().add(storico);
		storico.setRuolo(this);

		return storico;
	}

	public Storico removeStorico(Storico storico) {
		getStoricos().remove(storico);
		storico.setRuolo(null);

		return storico;
	}

}