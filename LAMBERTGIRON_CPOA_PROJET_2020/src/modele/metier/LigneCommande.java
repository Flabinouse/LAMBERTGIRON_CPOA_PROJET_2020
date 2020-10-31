package modele.metier;

public class LigneCommande {
	private int idcom;
	private int idprod;
	private int quantite;
	private double tarifunit;

	public LigneCommande(int idcom, int idprod, int quantite, double tarifunit) {
		super();
		this.idcom = idcom;
		this.idprod = idprod;
		this.quantite = quantite;
		this.tarifunit = tarifunit;
	}

	public LigneCommande() {
	}

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		if (idcom <= 0) {
			throw new IllegalArgumentException("L'id commande est null ou negatif !");
		} else {
			this.idcom = idcom;
		}
	}

	public int getIdprod() {
		return idprod;
	}

	public void setIdprod(int idprod) {
		if (idprod <= 0) {
			throw new IllegalArgumentException("L'id produit est null ou negatif !");
		} else {
			this.idprod = idprod;
		}
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		if (quantite <= 0) {
			throw new IllegalArgumentException("La quantite est nulle ou negative !");
		} else {
			this.quantite = quantite;
		}
	}

	public double getTarifunit() {
		return tarifunit;
	}

	public void setTarifunit(double tarifunit) {
		if (tarifunit <= 0) {
			throw new IllegalArgumentException("Le tarif unitaire est null ou negatif !");
		} else {
			this.tarifunit = tarifunit;
		}
	}

	@Override
	public String toString() {
		return "LigneCommande [idcom=" + idcom + ", idprod=" + idprod + ", quantite=" + quantite + ", tarifunit="
				+ tarifunit + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneCommande other = (LigneCommande) obj;
		if (idcom != other.idcom)
			return false;
		if (idprod != other.idprod)
			return false;
		if (Double.doubleToLongBits(tarifunit) != Double.doubleToLongBits(other.tarifunit))
			return false;
		return true;
	}

}
