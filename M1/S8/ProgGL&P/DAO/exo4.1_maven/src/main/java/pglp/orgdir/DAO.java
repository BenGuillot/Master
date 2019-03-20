package pglp.orgdir;

import java.sql.Connection;

public abstract class DAO <T> {
	protected Connection connect = null;//DriverManager.getConnection();
	
	public abstract T create (T obj);
	public abstract T find (String nom);
	public abstract T update (T obj);
	public abstract void delete (T obj);
}
