package pglp.orgdir;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public static void main(String[] args) {
    	Personnel p1 = new Personnel.Builder("Jean-Michel", "Test1").build();
    	Personnel p2 = new Personnel.Builder("Jean-Michel", "Test2").build();
    	DAO <Personnel> personnelDAO = new PersonnelDAO();
    	personnelDAO.create(p1);
    	personnelDAO.create(p2);
    }
}