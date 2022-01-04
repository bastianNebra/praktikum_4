package praktikum_4;

import java.util.List;

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
			
			print(Artikel.findByIdWithLiferung(em, 8));

			print("\n");
			
			//Eine bestimte Lieferant Lesen, mit zugehoerige Liferungen
			
			print(Lieferant.LieferantById(em, 5));
		

			 

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
