package modele.metier;

public class Produit {
	private int idproduit;
	private String nom;
	private String description;
	private float tarif;
	private String visuel;
	private int idcategorie;

	public Produit(int idproduit, String nom, String description, float tarif, String visuel, int idcategorie) {
		super();
		this.idproduit = idproduit;
		this.nom = nom;
		this.description = description;
		this.tarif = tarif;
		this.visuel = visuel;
		this.idcategorie = idcategorie;
	}

	public Produit() {
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom == null || nom.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom est vide !");
		} else {
			this.nom = nom;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("La description est vide !");
		} else {
			this.description = description;
		}
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		if (tarif <= 0) {
			throw new IllegalArgumentException("Le tarif est null ou negatif !");
		} else {
			this.tarif = tarif;
		}
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		if (visuel == null || visuel.trim().isEmpty()) {
			throw new IllegalArgumentException("Le visuel est vide !");
		} else {
			this.visuel = visuel;
		}
	}

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		if (idcategorie <= 0) {
			throw new IllegalArgumentException("L'id categorie est null ou negatif !");
		} else {
			this.idcategorie = idcategorie;
		}
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (idproduit != other.idproduit)
			return false;
		if (idcategorie != other.idcategorie)
			return false;
		if (Float.floatToIntBits(tarif) != Float.floatToIntBits(other.tarif))
			return false;
		return true;
	}

}
