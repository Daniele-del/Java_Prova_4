package orari;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Orari {

	Map<String,Percorso> percorsi = new HashMap<>();
	LinkedList<Treno> treni = new LinkedList<>();
	
	public Percorso creaPercorso(String codice, String categoria) {
		Percorso nuovo = new Percorso (codice, categoria, this);
		percorsi.put(codice, nuovo);	
		return nuovo;
	}
	
	public Percorso getPercorso(String codice) throws PercorsoNonValido {
		return percorsi.get(codice);
	}
	
	public Collection<Percorso> getPercorsi() {
		return percorsi.values();
	}
	
	public Treno nuovoTreno(String codice, int giorno, int mese, int anno ) throws PercorsoNonValido {
		if(percorsi.containsKey(codice)) {
			Treno nuovo = new Treno(percorsi.get(codice), giorno, mese, anno);
			treni.add(nuovo);
			return nuovo;
		} else {
			throw new PercorsoNonValido();
		}
	}
	
	public LinkedList<Treno> treniPerPercorso(Percorso percorso){
		LinkedList<Treno> treniPerPercorso = new LinkedList<>();
		for(Treno temp : treni ) {
			if(temp.getPercorso().equals(percorso)) {
				treniPerPercorso.add(temp);
			}
		}
		return treniPerPercorso;
	}

	public LinkedList<String> classificaRitardi() throws StazioneNonValida{
		LinkedList<String> classifica = new LinkedList<>();
		String stat;
		for(String tmp : percorsi.keySet()) {
			stat = tmp + ": "+ percorsi.get(tmp).ritardoMedio();
			classifica.add(stat);
		}
		return classifica;
	}
	
	
	
}
