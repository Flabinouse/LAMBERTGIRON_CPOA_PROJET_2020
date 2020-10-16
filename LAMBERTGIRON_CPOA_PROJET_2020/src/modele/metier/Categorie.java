package modele.metier;

public class Categorie {
	private int id_categorie;
	private String titre;
	private String visuel;

	public Categorie(int id_categorie, String titre, String visuel) {
		super();
		this.id_categorie = id_categorie;
		this.titre = titre;
		this.visuel = visuel;
	}

	public Categorie() {

	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		if (titre == null || titre.trim().isEmpty()) {
			throw new IllegalArgumentException("Le titre est vide !");
		} else {
			this.titre = titre;
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

	@Override
	public String toString() {
		return titre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (id_categorie != other.id_categorie)
			return false;
		return true;
	}

}
