package it.polito.tdp.crimes.model;

public class Agente {
	private int id;
	private boolean occupato;
	/**
	 * @param id
	 */
	public Agente(int id) {
		super();
		this.id = id;
		this.occupato = false;
	}

	public int getId() {
		return id;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
	
	
	
	
}
