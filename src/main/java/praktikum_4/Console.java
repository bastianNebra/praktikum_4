package praktikum_4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Console {
	private static final String ENTITY_MANAGER_PERSISTENCES = "praktikum_4";

	public static void main(String[] args) {

		print("___PROGRAMM START___");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_PERSISTENCES);
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			
			
			print("____CONNECTET____");



			

		} catch (Exception e) {
			e.printStackTrace();
			print(e.getMessage());
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// Function sysout
	public static void print(Object stg) {
		System.out.println(stg);
	}
}
