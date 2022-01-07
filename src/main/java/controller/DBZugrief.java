package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBZugrief {
	private static final String ENTITY_MANAGER_PERSISTENCES = "praktikum_4";

	//Funktion indem view die Verbindung mit bd machen
	public static EntityManager dbConnect(){
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_PERSISTENCES);
			em = emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return em;
	}

	
}
