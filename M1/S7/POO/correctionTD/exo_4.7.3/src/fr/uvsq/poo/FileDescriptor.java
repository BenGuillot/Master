package fr.uvsq.poo;

public abstract class FileDescriptor {
	String name;
	
	public FileDescriptor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name; 
	}
	
	abstract public int getSize();
	
	public String toString() {
		return this.getName();
	}
	
	public boolean isDirectory() {
		return "class fr.uvsq.poo.Directory".equals(this.getClass().toString());
	}
}
