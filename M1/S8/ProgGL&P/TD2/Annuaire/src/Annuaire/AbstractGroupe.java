package Annuaire;

import java.io.Serializable;

public abstract class AbstractGroupe implements Serializable {
	public AbstractGroupe() {}
	
	public abstract void affichageProfondeur();
	public abstract void affichageLargeur();
}
