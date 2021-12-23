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
		try{
			em = emf.createEntityManager();
			print("____CONNECTET____");
			
			List<Artikel> artikels = em.createNamedQuery("Artikel.findAll",Artikel.class).getResultList();
			for (Artikel artikel : artikels) {
				print(artikel);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (em !=null) {
				em.close();
			}
		}
	}
	
	private static void print(praktikum_4.Artikel artikel) {
		System.out.println(artikel);
		
	}

	//Function sysout
	public static void print(String stg) {
		System.out.println(stg);
	}
}
