package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Crime {
	
	private District districtId;
	private String type;
	private LocalDateTime time;
	private boolean risolto;
	/**
	 * @param districtId
	 * @param type
	 * @param localDate 
	 */
	public Crime(District districtId, String type, LocalDateTime localDate) {
		super();
		this.districtId = districtId;
		this.type = type;
		this.time = localDate;
		this.risolto = false;
	}
	public District getDistrictId() {
		return districtId;
	}
	public String getType() {
		return type;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public boolean isRisolto() {
		return risolto;
	}
	public void setRisolto(boolean risolto) {
		this.risolto = risolto;
	}
	
	
	
	
}
