package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the storico database table.
 * 
 */
@Entity
@NamedQuery(name="Storico.findAll", query="SELECT s FROM Storico s")
public class Storico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStorico;

	@Temporal(TemporalType.DATE)
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	private Date dataInizio;

	//bi-directional many-to-one association to Impiegato
	@ManyToOne
	@JoinColumn(name="matricola")
	private Impiegato impiegato;

	//bi-directional many-to-one association to Ruolo
	@ManyToOne
	@JoinColumn(name="idRuolo")
	private Ruolo ruolo;

	public Storico() {
	}

	public int getIdStorico() {
		return this.idStorico;
	}

	public void setIdStorico(int idStorico) {
		this.idStorico = idStorico;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Impiegato getImpiegato() {
		return this.impiegato;
	}

	public void setImpiegato(Impiegato impiegato) {
		this.impiegato = impiegato;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

}