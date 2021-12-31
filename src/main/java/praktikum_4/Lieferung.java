package praktikum_4;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the LIEFERUNG database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Lieferung.findAll", query = "SELECT l FROM Lieferung l"),
//		@NamedQuery(name = "Lieferung.findById", query = "SELECT l FROM Lieferung l WHERE l.lnr = :lNr"),
//	@NamedQuery(name = "Lieferung.LieferungVonArtikel", query = "SELECT l FROM Lieferung l WHERE l.anr LIKE :aid")
})
public class Lieferung implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LieferungPK id;

	private BigDecimal preis;

	// bi-directional many-to-one association to Artikel
	@ManyToOne
	@JoinColumn(name = "ANR")
	private Artikel artikel;

	// bi-directional many-to-one association to Lieferant
	@ManyToOne
	@JoinColumn(name = "LNR")
	private Lieferant lieferant;

	public Lieferung() {
	}
	
	

	public Lieferung(LieferungPK id, BigDecimal preis, Artikel artikel, Lieferant lieferant) {
		super();
		this.id = id;
		this.preis = preis;
		this.artikel = artikel;
		this.lieferant = lieferant;
	}



	public LieferungPK getId() {
		return this.id;
	}

	public void setId(LieferungPK id) {
		this.id = id;
	}

	public BigDecimal getPreis() {
		return this.preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public Artikel getArtikel() {
		return this.artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Lieferant getLieferant() {
		return this.lieferant;
	}

	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	// Lieferung Anlegen
	public static void LieferungAnlegen(EntityManager em, Lieferung l) {
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
	}

	// Liferung Lesen
	public static List<Lieferung> LieferungLesen(EntityManager em) {
		return em.createNamedQuery("Lieferung.findAll", Lieferung.class).getResultList();
	}

	// Eine Bestimte Lieferung lesen
	public static Lieferung LieferungIdLesen(EntityManager em, int idL) {
		return em.find(Lieferung.class, idL);
//		return em.createNamedQuery("Lieferung.findById", Lieferung.class).setParameter("lNr", idL).getSingleResult();
	}

	// Alle Lieferung zu einer Artikel Nummer Lesen
	public static List<Lieferung> LieferungVonArtikel(EntityManager em, int aid) {
		return em.createNamedQuery("Lieferung.LieferungVonArtikel", Lieferung.class).setParameter("aid", aid)
				.getResultList();
	}

	// Lieferung Löeschen
	public static void LieferungLoeschen(EntityManager em, int idL) {
		Lieferung l = em.find(Lieferung.class, idL);
		em.getTransaction().begin();
		em.remove(l);
		em.getTransaction().commit();
	}

	@Override
	public String toString() {
		return "Lieferung [id=" + id + ", preis=" + preis + ", artikel=" + artikel + ", lieferant=" + lieferant + "]";
	}

}