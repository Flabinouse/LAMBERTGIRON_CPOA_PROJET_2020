package modele.metier;

public class Produit {
	private int id_produit;
	private String nom;
	private String description;
	private float tarif;
	private String visuel;
	private int id_categorie;

	public Produit(int id_produit, String nom, String description, float tarif, String visuel, int id_categorie) {
		super();
		this.id_produit = id_produit;
		this.nom = nom;
		this.description = description;
		this.tarif = tarif;
		this.visuel = visuel;
		this.id_categorie = id_categorie;
	}

	public Produit() {
	}

	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
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

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		if (id_categorie <= 0) {
			throw new IllegalArgumentException("L'id categorie est null ou negatif !");
		} else {
			this.id_categorie = id_categorie;
		}
	}

	@Override
	public String toString() {
		return "Produit [id_produit=" + id_produit + ", nom=" + nom + ", description=" + description + ", tarif="
				+ tarif + ", visuel=" + visuel + ", id_categorie=" + id_categorie + "]";
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
		if (id_produit != other.id_produit)
			return false;
		if (id_categorie != other.id_categorie)
			return false;
		if (Float.floatToIntBits(tarif) != Float.floatToIntBits(other.tarif))
			return false;
		return true;
	}

}
