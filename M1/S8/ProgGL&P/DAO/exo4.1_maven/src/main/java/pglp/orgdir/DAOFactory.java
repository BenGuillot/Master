package pglp.orgdir;

public class DAOFactory {
	public static DAO<Personnel> createPersonnelDAO(){
		return new PersonnelDAO();
	}
}
