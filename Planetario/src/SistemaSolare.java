import java.util.ArrayList;

public class SistemaSolare {

	private final static int MAX_PIANETE = 26000;
	
	private Stella stella;
	private ArrayList<Pianeta> pianete = new ArrayList<>();
	private double massaTotale = 0;
	private Punto sommaPosizioni = new Punto(0, 0);
	private Punto centroMassa = new Punto(0, 0);
	
	public SistemaSolare(Stella stella) {
		this.stella = stella;
	}

	public Stella getStella() {
		return stella;
	}

	public void setStella(Stella stella) {
		this.stella = stella;
	}
	
	public double getMassaTotale() {
		return massaTotale;
	}

	public Punto getSommaPosizioni() {
		return sommaPosizioni;
	}

	public Punto getCentroMassa() {
		return centroMassa;
	}

	
	public boolean aggiungiPianeta(Pianeta nuovoPianeta) {
		if(pianete.size() < MAX_PIANETE) {
			if(checkNomiDoppi(nuovoPianeta.getNome()) == false) {
				pianete.add(nuovoPianeta);
				aggiornaMassa(nuovoPianeta.getMassa(), nuovoPianeta.getPunto(), 1);
				return true;
			}
			
			else return false;
			
		}
		return false;
	}
	
	public boolean checkNomePianeta(String nomeCorpo) {
		
		for(int i = 0; i < pianete.size(); i++) {
			if(pianete.get(i).getNome().equalsIgnoreCase(nomeCorpo)) return true;
		}
		
		return false;
		
	}
	
	public boolean checkNomiDoppi(String nomeCorpo) {
		for(int i = 0; i < pianete.size(); i++) {
			if(pianete.get(i).getNome().equalsIgnoreCase(nomeCorpo)) return true;
			else {
				for(int j = 0; j < pianete.get(i).getLuna().size(); j++) {
					if (pianete.get(i).getLuna().get(j).getNome().equalsIgnoreCase(nomeCorpo)) return true;
					else return false;
				}
			}
		}
		
		return false;
	}
	
	public Pianeta trovaPianeta(String nomePianeta) {
		
		for(int i = 0; i < pianete.size(); i++) {
			if (pianete.get(i).getNome().equalsIgnoreCase(nomePianeta))
			return pianete.get(i);
		}
		
		return null;
		
	}
	
	public boolean eliminaPianeta(String nomePianeta) {
		
		if(checkNomePianeta(nomePianeta) == true) {
			aggiornaMassa(trovaPianeta(nomePianeta).getMassa(), trovaPianeta(nomePianeta).getPunto(), 0);
			pianete.remove(trovaPianeta(nomePianeta));
			return true;
		}
		
		else return false;
		
	}
	
	public boolean aggiungiLuna(Luna nuovaLuna, String nomePianeta) {
		
		if(checkNomePianeta(nomePianeta) == true) {
			if(checkNomiDoppi(nuovaLuna.getNome()) == false) {
				if(trovaPianeta(nomePianeta).aggiungiLuna(nuovaLuna) == true) {
					aggiornaMassa(nuovaLuna.getMassa(), nuovaLuna.getPunto(), 1);
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	public void aggiornaMassa(double massa, Punto puntoCorrente, int stato) {
		if (stato == 1) {
			massaTotale += massa;
			sommaPosizioni.setX(sommaPosizioni.getX() + (puntoCorrente.getX() * massa));
			sommaPosizioni.setY(sommaPosizioni.getY() + (puntoCorrente.getY() * massa));
			
		}
		else {
			massaTotale -= massa;
			sommaPosizioni.setX(sommaPosizioni.getX() - (puntoCorrente.getX() * massa));
			sommaPosizioni.setY(sommaPosizioni.getY() - (puntoCorrente.getY() * massa));
		}
		
		calcolaCentroMassa();
		
		
	}
	
	public void calcolaCentroMassa() {
		
		centroMassa.setX(massaTotale / sommaPosizioni.getX());
		centroMassa.setY(massaTotale / sommaPosizioni.getY());
		
	}
	
	public void stampaTuttiPianete() {
		for(int i = 0; i < pianete.size(); i++) {
			System.out.println("nome: " + pianete.get(i).getNome());
			System.out.println("massa: " + pianete.get(i).getMassa());
			System.out.println(pianete.get(i).getPunto());
		}
	}
	
}
