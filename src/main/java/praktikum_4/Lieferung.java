package praktikum_4;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LIEFERUNG database table.
 * 
 */
@Entity
@NamedQuery(name="Lieferung.findAll", query="SELECT l FROM Lieferung l")
public class Lieferung implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LieferungPK id;

	private BigDecimal preis;

	//bi-directional many-to-one association to Artikel
	@ManyToOne
	@JoinColumn(name="ANR")
	private Artikel artikel;

	//bi-directional many-to-one association to Lieferant
	@ManyToOne
	@JoinColumn(name="LNR")
	private Lieferant lieferant;

	public Lieferung() {
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

}