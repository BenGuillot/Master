package pglp.orgdir;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersonnelDAO extends DAO<Personnel>{
	private List<Personnel> listePersonnels;
	
	public PersonnelDAO () {
		this.listePersonnels = new ArrayList<Personnel>();
	}
	
	@Override 
	public Personnel create(Personnel obj) {
		this.listePersonnels.add(obj);
		
		try {
			final FileOutputStream fichier = new FileOutputStream("data.save");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			int i;
			for (i=0;i<this.listePersonnels.size(); i++) {
				oos.writeObject(this.listePersonnels.get(i));
			}
		}
		catch (Exception e) {}
		System.out.println("Sauvegarde effectuée");
		return obj;
	}
	
	@Override
	public Personnel find(String nom) {
		Personnel p=new Personnel.Builder("test", "test").build();
		try {
			final FileInputStream fichier = new FileInputStream("data.save");
			ObjectInputStream oos = new ObjectInputStream(fichier);
			int i;
			
			for (i=0;i<this.listePersonnels.size(); i++) {
				p=(Personnel)oos.readObject();
				if (p.getFirstname()==nom) return p;
			}
		}
		catch (Exception e) {}
		System.out.println("Recherche effectuée");
		return null;
	}
	
	@Override
	public Personnel update(Personnel obj) {return obj;}
	
	@Override 
	public void delete(Personnel obj) {}
}
