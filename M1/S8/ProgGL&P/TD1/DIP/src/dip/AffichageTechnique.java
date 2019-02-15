package dip;

import java.time.*;

public class AffichageTechnique implements Affichage {
	AffichageTechnique(){
	}

	@Override
	public void affichageMessage() {
		// TODO Auto-generated method stub
		System.out.println(LocalDateTime.now() + " Debut metier\n");
		//// Traitement ...
		System.out.println(LocalDateTime.now() + " Fin metier\n");
	}

}
