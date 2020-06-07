package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	EventsDao dao;
	Graph<District, DefaultWeightedEdge> grafo;
	
	
	public Model() {
		dao = new EventsDao();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public void creaGrafo(int year) {
		
		
		List<District> vertici = this.dao.listVertex(year);
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(District v1: vertici) {
			for(District v2: vertici) {
				if(!v1.equals(v2)) {
					
					Double distanza = LatLngTool.distance(new LatLng(v1.getLat(),v1.getLon()), new LatLng(v2.getLat(), v2.getLon()), LengthUnit.KILOMETER);
					
					Graphs.addEdge(this.grafo, v1, v2, distanza);
				}
				
			}
		}
		
	}
	
	public String getLista() {
		
		
		String lista = "";
		for(District v: this.grafo.vertexSet()) {
			
			lista+="\nDISTRETTO " + v.getId()+":";
			lista+=this.getListaPerDistretto(v);
			lista+="--------------------------------------------------------------------";
		}
		return lista;
	}
	
	
	
	private String getListaPerDistretto(District v) {
		
		String lista="";
		List<Vicino> vicini = new ArrayList<>();
		for(District vicino: Graphs.neighborListOf(this.grafo, v) ) {
			
			vicini.add(new Vicino(vicino, this.grafo.getEdgeWeight(this.grafo.getEdge(v, vicino))));
			
		}
		Collections.sort(vicini);
		
		for(Vicino vic: vicini) {
			
			lista+="\n Vicino: "+vic.getVicino().getId()+" ("+vic.getDistanza()+")"; 
		}
		return lista;
	}
	
	

	public void simula(LocalDate ld, int k) {
		Simulator sim = new Simulator(this, k, ld);
		
	}
	public List<Crime> getCrimes(LocalDate ld) {
		
		return dao.getCrimesByDate(ld);
		
	}
	
	public District getDistrettoPartenza(int year) {
		
		int id = dao.getDistrettoPartenza(year);
		for(District d: this.grafo.vertexSet()) {
			
			if(d.getId()==id) {
				return d;
			}
		}
		return null;
	}

	public List<Integer> getYear() {
		
		return this.dao.listYears();
	}

	public Graph<District, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	
	
}
