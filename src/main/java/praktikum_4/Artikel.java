package praktikum_4;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the ARTIKEL database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a ORDER BY a.anr"),
		@NamedQuery(name = "Artike.findArtikelId", query = "SELECT a FROM Artikel  a WHERE a.anr = :idNr"),
		@NamedQuery(name = "Artikel.findGroeseAlPres", query = "SELECT a FROM Artikel a  WHERE a.preis  > :preisgeb"),
		@NamedQuery(name = "Artikel.findZwiZwei", query = "SELECT a FROM Artikel  a WHERE a.anr BETWEEN   :anr1 AND :anr2"),
		@NamedQuery(name = "Artikel.Aktualisieren", query = "SELECT a FROM Artikel  a ORDER BY a.anr ASC"),
})

public class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int anr;

	@Temporal(TemporalType.DATE)
	private Date angelegt;

	private String bezeichnung;

	private BigDecimal preis;

	// bi-directional many-to-one association to Lieferung
	@OneToMany(mappedBy = "artikel", 
			fetch = FetchType.LAZY, 
			cascade = { CascadeType.REFRESH })
	private static List<Lieferung> lieferungs;

	public Artikel() {
	}

	public Artikel(int anr, Date angelegt, String bezeichnung, BigDecimal preis) {
		super();
		this.anr = anr;
		this.angelegt = angelegt;
		this.bezeichnung = bezeichnung;
		this.preis = preis;
	}

	public long getAnr() {
		return this.anr;
	}

	public void setAnr(int anr) {
		this.anr = anr;
	}

	public Date getAngelegt() {
		return this.angelegt;
	}

	public void setAngelegt(Date angelegt) {
		this.angelegt = angelegt;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	@SuppressWarnings("static-access")
	public List<Lieferung> getLieferungs() {
		return this.lieferungs;
	}

	@SuppressWarnings("static-access")
	public void setLieferungs(List<Lieferung> lieferungs) {
		this.lieferungs = lieferungs;
	}

	public Lieferung addLieferung(Lieferung lieferung) {
		getLieferungs().add(lieferung);
		lieferung.setArtikel(this);

		return lieferung;
	}

	public Lieferung removeLieferung(Lieferung lieferung) {
		getLieferungs().remove(lieferung);
		lieferung.setArtikel(null);

		return lieferung;
	}

	// Eine Artikel Anlegen
	public static void ArtikelInsert(EntityManager en, Artikel a) {
		en.getTransaction().begin();
		en.persist(new Artikel(a.anr, a.angelegt, a.bezeichnung, a.preis));
		en.getTransaction().commit();

	}
	
	// Eine Artikel Löschen
		public static void ArtikelAllLoeschen(EntityManager en) {
			en.clear();
		}

	// Eine Artikel Löschen
	public static void ArtikelLoeschen(EntityManager en, int idNr) {
		Artikel a = en.find(Artikel.class, idNr);
		en.getTransaction().begin();
		en.remove(a);
		en.getTransaction().commit();

	}

	// Eine Artikel Mit Liferung Löschen
	public static void ArtikelAendern(EntityManager en, int idNr) {
		Artikel a = en.find(Artikel.class, idNr);
		@SuppressWarnings("deprecation")
		Artikel newa = new Artikel(idNr,new Date(11,2,21),"PC",new BigDecimal(100));
		
		en.getTransaction().begin();
		a.setBezeichnung(newa.bezeichnung);
		a.setPreis(newa.preis);
		en.getTransaction().commit();

	}
	
	public static List<Artikel> ArtikelAktuali(EntityManager em) {
		return em.createNamedQuery("Artikel.Aktualisieren",Artikel.class).getResultList();
	}
	
	// Eine Artikel Mit Liferung Löschen
		public static void ArtikelLiefLoeschen(EntityManager en, int idNr) {
			Artikel a = en.find(Artikel.class, idNr);
			Lieferung l = en.find(Lieferung.class, idNr);
			en.getTransaction().begin();
			en.remove(a);
			en.remove(l);
			en.getTransaction().commit();

		}

	// Find alle Artikel in unsere Database
	public static List<Artikel> findAll(EntityManager en) throws SQLException {
		return en.createNamedQuery("Artikel.findAll", Artikel.class).getResultList();
	}

	// Artikel mit Lieferung Zeigen
	public static Object findByIdWithLiferung(EntityManager em,int idNr) {
		String query = "SELECT * FROM Lieferung  WHERE ANR = ?";
		Artikel a = null;
		Lieferung l = null;
		
		try {
			a = em.find(Artikel.class, idNr);
			l = (Lieferung) em.createNativeQuery(query,Lieferung.class).setParameter(1, idNr).getSingleResult();
		} catch (Exception e) {
			
		}
		
		
		return a +"\n Lieferungen : "+ l;
	}

	// alle Artikel mit einem Preis oberhalb eines gegebenen Preises lesen
	public static List<Artikel> findGroeseAlPres(EntityManager en, BigDecimal preis) throws SQLException {
		return en.createNamedQuery("Artikel.findGroeseAlPres", Artikel.class).setParameter("preisgeb", preis)
				.getResultList();
	}

	// Artikel Zwieschen Zwei number geben
	public static List<Artikel> findByIdZwichen(EntityManager en, int anr1, int anr2) {
		return en.createNamedQuery("Artikel.findZwiZwei", Artikel.class).setParameter("anr1", anr1)
				.setParameter("anr2", anr2).getResultList();
	}

	@Override
	public String toString() {
		return "[anr=" + anr + ", angelegt=" + angelegt + ", bezeichnung=" + bezeichnung + ", preis=" + preis+ "]";
	}

}