package orari;

import java.util.Collections;
import java.util.LinkedList;

public class Percorso {


	private String codice;
	private String categoria;
	private boolean straordinario = false;
	private Orari dino;
	
	LinkedList<Fermata> fermate = new LinkedList<>();


	public Percorso(String codice, String categoria, Orari dino) {
		this.codice = codice;
		this.categoria = categoria;
		this.dino = dino;
	}
	
	public String getCodice() {
		return codice;
	}

	public String getCategoria() {
		return categoria;
	}

	public boolean isStraordinario() {
		return straordinario;
	}

	public void setStraordinario(boolean straordinario) {
		this.straordinario = straordinario;
	}
	
	public Fermata aggiungiFermata(String stazione, int ora, int minuti) {
		Fermata fermata = new Fermata(stazione,ora, minuti);
		fermate.add(fermata);
		return fermata;
	}
	
	public LinkedList<Fermata> getFermate(){
		fermate.sort((a,b) -> {
			if(a.getOre() == b.getOre()) {
				return Integer.compare(a.getMinuti(), b.getMinuti());
			} else  {
				return Integer.compare(a.getOre(), b.getOre());
			}
		});
		return fermate;
	}

	public LinkedList<Treno> getTreni(){
		LinkedList<Treno> treni = dino.treniPerPercorso(this);
		treni.sort((a,b) -> {
			if(a.getAnno() == b.getAnno()) {
				if(a.getMese() == b.getMese()) {
					return Integer.compare(a.getGiorno(), b.getGiorno());
				} else {
					return Integer.compare(a.getMese(), b.getMese());
				}
			} else {
				return Integer.compare(a.getAnno(), b.getAnno());
			}
		});
		Collections.reverse(treni);
		return treni;
	}
	
	public Fermata getFermata(String nome) {
		for (Fermata f : fermate) {
			if(f.getStazione().equals(nome))
				return f;
		}
		
		return null;
	}
	
	public int ritardoMedio() throws StazioneNonValida {
		double avg = 0;
		LinkedList <Treno> tp = dino.treniPerPercorso(this);
		for(Treno t : tp) {
			avg += t.ritardoFinale();
		}	
		return (int) (avg/tp.size());
	}
	
	public int ritardoMassimo() throws StazioneNonValida {
		int max = 0;
		LinkedList <Treno> tp = dino.treniPerPercorso(this);
		for(Treno t : tp) {
			if(t.ritardoFinale() > max) {
				max = t.ritardoFinale();
			}
		}
		return max;
	}
	
	
	
	
	
	
}
