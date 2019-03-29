package fr.uvsq.poo;

public class RegularFile extends FileDescriptor {
	int size;
	
	public RegularFile(String name, int size) {
		super(name);
		this.size = size;
	}

	@Override
	public int getSize() {
		return size;
	}
}
