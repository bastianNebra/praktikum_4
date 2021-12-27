package praktikum_4;

import java.math.BigDecimal;
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

			List<Artikel> artikels = Artikel.ArtikelAktuali(em);
			for (Artikel artikel : artikels) {
				print(artikel);
			}

			print("\n");

			// Artikel mit preise großer als
			BigDecimal preis = new BigDecimal(200);
			List<Artikel> artikels1 = Artikel.findGroeseAlPres(em, preis);
			for (Artikel artikel : artikels1) {
				print(artikel);
			}

			print("\n");

			// Artikel zwischen 2 eingabe
			List<Artikel> artikels2 = Artikel.findByIdZwichen(em, 2, 10);
			for (Artikel artikel : artikels2) {
				print(artikel);
			}


			Artikel.ArtikelAendern(em, 13);
			 

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
