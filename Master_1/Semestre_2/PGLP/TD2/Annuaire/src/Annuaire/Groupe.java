package Annuaire;

import java.util.ArrayList;
import java.util.Iterator;


public class Groupe extends AbstractGroupe {
	
	private ArrayList<AbstractGroupe> groupe = new ArrayList<AbstractGroupe>();
	
	public Groupe() {
	}
	
	@Override
	public void affichageProfondeur() {
		Iterator it = groupe.iterator();
		while(it.hasNext()) {
			AbstractGroupe ag = (AbstractGroupe) it.next();
			ag.affichageProfondeur();
		}
		      
		      
		// for(AbstractGroupe ag : groupe) {
			// ag.affichageProfondeur();}
	}
	
	@Override
	public void affichageLargeur() {	
	}
	
	public void add(AbstractGroupe gr) {
		this.groupe.add(gr);
	}

}
