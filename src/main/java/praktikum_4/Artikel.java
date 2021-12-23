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
@NamedQueries({
	@NamedQuery(name="Artikel.findAll", query="SELECT a FROM Artikel a"),
})

public class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long anr;

	@Temporal(TemporalType.DATE)
	private Date angelegt;

	private String bezeichnung;

	private BigDecimal preis;

	//bi-directional many-to-one association to Lieferung
	@OneToMany(mappedBy="artikel")
	private List<Lieferung> lieferungs;

	public Artikel() {
	}

	public long getAnr() {
		return this.anr;
	}

	public void setAnr(long anr) {
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

	public List<Lieferung> getLieferungs() {
		return this.lieferungs;
	}

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
	
	//Find alle Artikel in unsere Database
		public static List<Artikel> findAll(EntityManager en) throws SQLException {
			return en.createQuery("Artikel.findAll",Artikel.class).getResultList();
		}

}