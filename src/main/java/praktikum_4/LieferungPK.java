package praktikum_4;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LIEFERUNG database table.
 * 
 */
@Embeddable
public class LieferungPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long lnr;

	@Column(insertable=false, updatable=false)
	private long anr;

	public LieferungPK() {
	}
	public long getLnr() {
		return this.lnr;
	}
	public void setLnr(long lnr) {
		this.lnr = lnr;
	}
	public long getAnr() {
		return this.anr;
	}
	public void setAnr(long anr) {
		this.anr = anr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LieferungPK)) {
			return false;
		}
		LieferungPK castOther = (LieferungPK)other;
		return 
			(this.lnr == castOther.lnr)
			&& (this.anr == castOther.anr);
	}
	
	

	@Override
	public String toString() {
		return "LieferungPK [lnr=" + lnr + ", anr=" + anr + "]";
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.lnr ^ (this.lnr >>> 32)));
		hash = hash * prime + ((int) (this.anr ^ (this.anr >>> 32)));
		
		return hash;
	}
}