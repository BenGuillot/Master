package OCP;

public class Manager extends Employe {
	private int subordonnés;

	public Manager(String nom, String adresse, int anciennete, int subordonnés) {
		super(nom, adresse, anciennete);
		// TODO Auto-generated constructor stub
		this.setSubordonnés(subordonnés);
	}

	public double getSubordonnés() {
		return subordonnés;
	}
	public void setSubordonnés(int subordonnés) {
		this.subordonnés = subordonnés;
	}
}