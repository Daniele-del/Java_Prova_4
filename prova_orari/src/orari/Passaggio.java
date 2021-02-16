package orari;

public class Passaggio {

	private String stazione;
	private int ora;
	private int minuti;
	private Treno treno;
	
	public Passaggio (String stazione, int ora, int minuti, Treno treno) {
		this.stazione = stazione;
		this.ora = ora;
		this.minuti = minuti;
		this.treno = treno;
		}
	
	public String getStazione() {
		return stazione;
	}
	public int getOra() {
		return ora;
	}
	public int getMinuti() {
		return minuti;
	}
	
	public int ritardo() throws StazioneNonValida {
		int ritardo = 0;
		Fermata fermata = treno.getPercorso().getFermata(stazione);
		if(fermata == null) {
			throw new StazioneNonValida();
		}
		if(fermata.getOre() == this.ora) {
			ritardo = fermata.getMinuti() - this.minuti;		
		} else {
			ritardo = ((fermata.getOre() - this.ora)*60) + (fermata.getMinuti() - this.minuti);
		}
		return Math.abs(ritardo);
	}


	
}
