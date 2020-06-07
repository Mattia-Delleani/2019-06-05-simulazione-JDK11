package it.polito.tdp.crimes.model;

public class Vicino implements Comparable<Vicino> {

	
	private District vicino;
	private Double distanza;
	/**
	 * @param vicino
	 * @param distanza
	 */
	public Vicino(District vicino, double distanza) {
		super();
		this.vicino = vicino;
		this.distanza = distanza;
	}
	public District getVicino() {
		return vicino;
	}
	public double getDistanza() {
		return distanza;
	}
	@Override
	public int compareTo(Vicino o) {
		// TODO Auto-generated method stub
		return - (this.distanza.compareTo(o.getDistanza()));
	}
	
	
	
	
}
