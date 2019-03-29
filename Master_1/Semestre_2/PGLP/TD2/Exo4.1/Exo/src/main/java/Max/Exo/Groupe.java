package Max.Exo;

import java.util.ArrayList;
import java.util.Iterator;


public class Groupe extends AbstractGroup {
	
	private ArrayList<AbstractGroup> groupe = new ArrayList<AbstractGroup>();
	
	public Groupe() {
	}
	
	@Override
	public void affichageProfondeur() {
		Iterator it = groupe.iterator();
		while(it.hasNext()) {
			AbstractGroup ag = (AbstractGroup) it.next();
			ag.affichageProfondeur();
		}
		      
		      
		// for(AbstractGroupe ag : groupe) {
			// ag.affichageProfondeur();}
	}
	
	@Override
	public void affichageLargeur() {	
	}
	
	public void add(AbstractGroup gr) {
		this.groupe.add(gr);
	}

}