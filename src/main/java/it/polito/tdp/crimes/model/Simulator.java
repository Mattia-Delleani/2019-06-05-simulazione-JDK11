package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.crimes.model.Evento.EventType;

public class Simulator {
	
	//input
	List<Crime> crimini;
	Map<Integer, Crime> map;
	Graph<District, DefaultWeightedEdge> grafo;
	District distrettoPartenza;
	
	int numeroAgenti = 0;
	
	//variabili del mondo
	
	List<Agente> agenti;
	
	//variabili risultato
	
	int malGestiti;
	
	//coda degli eventi
	
	PriorityQueue<Evento> queue;
	
	
	public Simulator(Model model, int k, LocalDate ld) {
		
		grafo = model.getGrafo();
		crimini = model.getCrimes(ld);
		distrettoPartenza = model.getDistrettoPartenza(ld.getYear());
		numeroAgenti = k;
	}
	
	public void init() {
		//carico la lista di agenti
		for(int i =0; i< numeroAgenti; i++) {
			
			agenti.add(new Agente(i+1));
		}
		
		
		map = new HashMap<>();
		int cont = 1;
		for(Crime c: crimini) {
			
			map.put(cont, c);
			cont++;
		}
		//carico nella coda degli eventi
		for(int i = 0; i<agenti.size(); i++) {
			
			Evento e = new Evento(agenti.get(i), i+1, distrettoPartenza, EventType.INVIO_AGENTE, crimini.get(i).getTime());
			queue.add(e);
		}
		
		
	}
	
	public void run() {
		
		
		while(!queue.isEmpty()) {
			
			Evento e = queue.poll();
			
			processEvent(e);
			
		}
	}

	private void processEvent(Evento e) {
		
		switch(e.getType()) {
		
		case INVIO_AGENTE:
			
			District destinazione = e.getCrimine().getDistrictId();
			long tempoTragitto = (long) this.grafo.getEdgeWeight(this.grafo.getEdge(distrettoPartenza, destinazione))/60;
			if(tempoTragitto>1/4) {
				
				this.malGestiti++;
			}
			
			
			Evento e2 = new Evento(e.getAgente(), e.getCrimine(),destinazione,EventType.ARRIVO_AGENTE, e.getTime().plusHours(tempoTragitto));
			queue.add(e2);
			break;
		case ARRIVO_AGENTE:
			
			
			
			break;
		}
		
	}
	

}
