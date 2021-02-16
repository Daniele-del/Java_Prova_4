package orari;

import java.util.LinkedList;

public class Treno {

	private Percorso percorso;
	private int giorno;
	private int mese;
	private int anno;
	
	LinkedList<Passaggio> passaggi = new LinkedList<>();
	
	public Treno(Percorso percorso, int giorno, int mese, int anno) {
		this.percorso = percorso;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	public int getMese() {
		return mese;
	}

	public int getAnno() {
		return anno;
	}

	public int getGiorno() {
		return giorno;
	}

	public Percorso getPercorso() {
		return percorso;
	}
	
	public Passaggio registraPassaggio(String stazione, int ora, int minuti) throws StazioneNonValida {
		boolean exist = false;
		for(Fermata tmp : percorso.getFermate()) {
			if(tmp.getStazione().equals(stazione)) {
				exist = true;
			}
		}
		if(exist == false) {
			throw new StazioneNonValida();
		}
		Passaggio nuovo = new Passaggio(stazione, ora, minuti,this);
		passaggi.add(nuovo); 
		return nuovo;
	}
	
	public boolean arrivato() {
		if(passaggi.getLast().getStazione() == percorso.getFermate().getLast().getStazione()) {
			return true;
		}
		return false;
	}
	
	public int ritardoMassimo() throws StazioneNonValida {
		int max = 0;
		for(Passaggio p : passaggi) {
			if(p.ritardo() > max) {
				max = p.ritardo();
			}
		}
		return max;
	}
	
	public int ritardoFinale() throws StazioneNonValida {
		return passaggi.getLast().ritardo();
	}
	
	
	
	

	
	
	
}
