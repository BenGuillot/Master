package fr.uvsq.poo;

public class Main {
	public static void main(String[] args) {
		// Build file hierarchy
		Directory root = new Directory("/");
		Directory usr = new Directory("/bin");
		root.add(usr);
		Directory home = new Directory("/home");
		root.add(home);
		RegularFile home_tgz = new RegularFile("home.tgz", 183);
		root.add(home_tgz);
		Directory toto = new Directory("/home/toto");
		home.add(toto);
		Directory titi = new Directory("/home/titi");
		home.add(titi);
		RegularFile totoFile1 = new RegularFile("totoFile1", 10);
		toto.add(totoFile1);
		RegularFile totoFile2 = new RegularFile("totoFile2", 20);
		toto.add(totoFile2);
		RegularFile titiFile1 = new RegularFile("titiFile1", 100);
		titi.add(titiFile1);
		RegularFile titiFile2 = new RegularFile("titiFile2", 200);
		titi.add(titiFile2);
		
		// Print some size
		System.out.println("size(/) = " + root.getSize());
		System.out.println("size(/home) = " + home.getSize());
		System.out.println("size(home.tgz) = " + home_tgz.getSize());
		
		// Contains
		System.out.println(root + " contains " + home + " ? " + root.contains(home));
		System.out.println(root + " contains " + titi + " ? " + root.contains(titi));
		System.out.println(root + " contains " + titiFile1 + " ? " + root.contains(titiFile1));
		System.out.println(toto + " contains " + titi + " ? " + toto.contains(titi));
	
		// Add
		System.out.println("add " + root + " into " + toto + " = " + toto.add(root));
		System.out.println("add " + toto + " into " + toto + " = " + toto.add(root));
		System.out.println("add " + home_tgz + " into " + toto + " = " + toto.add(home_tgz));
	}
}
