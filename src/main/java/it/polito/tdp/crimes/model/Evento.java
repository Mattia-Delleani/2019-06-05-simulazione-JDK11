package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento> {

	
	private Agente agente;
	private int iDcrimine;
	private District distretto;
	private EventType type;
	private LocalDateTime time;
	
	public enum EventType {
		
		INVIO_AGENTE, ARRIVO_AGENTE
	}

	/**
	 * @param agente
	 * @param crimine
	 * @param distretto
	 * @param type
	 * @param time
	 */
	public Evento(Agente agente, int iDcrimine, District distretto, EventType type, LocalDateTime time) {
		super();
		this.agente = agente;
		this.iDcrimine = iDcrimine;
		this.distretto = distretto;
		this.type = type;
		this.time = time;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public int getCrimine() {
		return iDcrimine;
	}

	public void setCrimine(int crimine) {
		this.iDcrimine = crimine;
	}

	public District getDistretto() {
		return distretto;
	}

	public void setDistretto(District distretto) {
		this.distretto = distretto;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}
	
	
}
