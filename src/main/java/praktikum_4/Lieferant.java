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
public class Lieferant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long lnr;

	private String name;

	private String plz;

	//bi-directional many-to-one association to Lieferung
	@OneToMany(mappedBy="lieferant")
	private List<Lieferung> lieferungs;

	public Lieferant() {
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

}