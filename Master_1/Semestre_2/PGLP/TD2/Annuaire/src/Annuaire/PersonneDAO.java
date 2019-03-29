package Annuaire;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAO extends DAO<Personne>{
	public static List<Personne> pers = new ArrayList<>();
	public String dataFile = "dataPersonne.txt";
	ObjectOutputStream out;
	
	public PersonneDAO() throws IOException {
		this.out = new ObjectOutputStream(
						new BufferedOutputStream(
							new FileOutputStream(dataFile)));
	}
	
	@Override
	public Personne create(Personne obj) {
		pers.add(obj);
		try {
			out.writeObject(obj) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(obj);
		return obj;
	}

	@Override
	public Personne find(String id) {
		
		return null;
	}

	@Override
	public Personne update(Personne obj) {
		return obj;
	}

	@Override
	public void delete(Personne obj) {}
	
}
