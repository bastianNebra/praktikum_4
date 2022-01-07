package praktikum_4;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LIEFERANT database table.
 * 
 */
@Entity
@NamedQuery(name="Lieferant.findAll", query="SELECT l FROM Lieferant l")
//@NamedQuery(name="Lieferant.findById", query="SELECT l FROM Lieferant l WHERE LNR = :lnr")
@NamedQuery(name="Lieferant.Zwieschen",query="SELECT l FROM Lieferant l WHERE l.lnr BETWEEN :lid1 AND :lid2 ")
@NamedQuery(name="Lieferant.Aktualisiert",query="SELECT l FRom Lieferant l ORDER BY l.lnr ASC ")
//@NamedQuery(name="Lieferung.findById",query="SELECT l FROM Lieferung l WHERE l.lnr LIKE :lid")
public class Lieferant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long lnr;

	private String name;

	private String plz;

	//bi-directional many-to-one association to Lieferung
	@OneToMany(mappedBy="lieferant",
			fetch = FetchType.EAGER,
			cascade = {CascadeType.ALL})
	private List<Lieferung> lieferungs;

	public Lieferant() {
	}
	

	public Lieferant(long lnr, String name, String plz) {
		super();
		this.lnr = lnr;
		this.name = name;
		this.plz = plz;
	}


	public long getLnr() {
		return this.lnr;
	}

	public void setLnr(long lnr) {
		this.lnr = lnr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlz() {
		return this.plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public List<Lieferung> getLieferungs() {
		return this.lieferungs;
	}

	public void setLieferungs(List<Lieferung> lieferungs) {
		this.lieferungs = lieferungs;
	}

	public Lieferung addLieferung(Lieferung lieferung) {
		getLieferungs().add(lieferung);
		lieferung.setLieferant(this);

		return lieferung;
	}

	public Lieferung removeLieferung(Lieferung lieferung) {
		getLieferungs().remove(lieferung);
		lieferung.setLieferant(null);

		return lieferung;
	}
	//Lieferant Anlegen
	public static void LieferantAnlegen(EntityManager em, Lieferant l) {
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
	}
	
	//Lieferant Lesen
	public static List<Lieferant> LieferantLesen(EntityManager em) {
		return em.createNamedQuery("Lieferant.findAll",Lieferant.class).getResultList();
	}
	
	//Eine Bestimte Lieferant lesen
	public static Object LieferantById(EntityManager em,int LNr) {
		String query = "SELECT * FROM Lieferant  WHERE LNR = ?";
		String query02 = "SELECT * FROM Lieferung WHERE LNR = ?";
		Lieferung li = null;
		
		Lieferant lt = (Lieferant) em.createNativeQuery(query,Lieferant.class).setParameter(1, LNr).getSingleResult();
		try {
			li = (Lieferung) em.createNativeQuery(query02,Lieferung.class).setParameter(1, LNr).getSingleResult();
			if (li == null) {
				System.out.println("Keine Lieferung vorhanden");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		return lt + "\n Lieferungen : "+li;
	}
	
	//Lieferanten Zwieschen Zwei Lieferant Nummer geben
	public static List<Lieferant> LieferantZwieschenZwei(EntityManager em, int lid1,int lid2){
		return em.createNamedQuery("Lieferant.Zwieschen",Lieferant.class).setParameter("lid1",lid1)
				.setParameter("lid2", lid2).getResultList();
	}
	
	//Liste von Lieferant Aktualisieren
	public static List<Lieferant> LLAktualisieren(EntityManager em) {
		return em.createNamedQuery("Lieferant.Aktualisiert",Lieferant.class).getResultList();
	}
	
	//Lieferant Aendern
	public static void LieferantAender(EntityManager em, int lid) {
		Lieferant lnew = new Lieferant(12,"Thomas Müller", "67657");
		Lieferant l = em.find(Lieferant.class, lid);
		
		em.getTransaction().begin();
		l.setName(lnew.getName());
		l.setPlz(lnew.getPlz());
		em.getTransaction().commit();
	}
	
	//Eine Lieferant Löschen
	public static void LieferantLoeschen(EntityManager em,int lid) {
		Lieferant l = em.find(Lieferant.class, lid);
		em.getTransaction().begin();
		em.remove(l);
		em.getTransaction().commit();
	}
	
	//Lieferant Mit Lieferungen Loeschen
	public static void deleteWithLieferung(EntityManager em,int lid) {
		Lieferung l = em.createNamedQuery("Lieferung.findById", Lieferung.class)
				.setParameter("lid", lid).getSingleResult();
		Lieferant lt = em.find(Lieferant.class, lid);
		em.getTransaction().begin();
		em.remove(l);
		em.remove(lt);
		em.getTransaction().commit();
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ LieferantName : "+ this.getName() +"; "+ "PLZ : "+ this.getPlz() + " ]";
	}

}