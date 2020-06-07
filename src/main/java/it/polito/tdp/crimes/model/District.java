package it.polito.tdp.crimes.model;

public class District{

	
	private int id;
	private double lat;
	private double lon;
	/**
	 * @param id
	 * @param lat
	 * @param lon
	 */
	public District(int id, double lat, double lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
	
	public District(int id) {
		this.id = id;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(int lon) {
		this.lon = lon;
	}
	
	
	
}
