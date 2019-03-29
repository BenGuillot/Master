package fr.uvsq.poo;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileDescriptor {
	List<FileDescriptor> children;
	
	public Directory(String name) {
		super(name);
		children = new ArrayList<FileDescriptor>();
	}
	
	public boolean contains(FileDescriptor f) {
		boolean isChild = (this == f);
		for (FileDescriptor child : this.children) {
			if (f == child) {
				isChild = true;
				break;
			}		
			if (child.isDirectory()) {
				isChild |= ((Directory) child).contains(f);
				if (isChild) break;
			}
		}
		return isChild;
	}
	
	public boolean add(FileDescriptor f) {
		boolean ret = false;
		if (f != this) {
			if (!f.isDirectory() || ((Directory) f).contains(this)) { 
				this.children.add(f);
				ret = true;
			}
		}
		return ret;
	}
	
	@Override
	public int getSize() {
		int size = 0;
		for (FileDescriptor child : this.children) {
			size += child.getSize();
		}
		return size;
	}
}
