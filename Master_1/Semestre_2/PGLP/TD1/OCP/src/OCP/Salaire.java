package OCP;

public class Salaire{

	Salaire(Employe e){
		e.setSalaire(e.getSalaire() + e.getAnciennete()*100);
	}

	Salaire(Vendeur v){
		super.setSalaire(super.getSalaire() + v.getCommission());
	}

	Salaire(Manager m){
		super.setSalaire(super.getSalaire() + super.getAnciennete()*100 + m.getSubordonnés()*100);
	}
}