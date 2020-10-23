package modele.metier;

public class Categorie {
	private int idcategorie;
	private String titre;
	private String visuel;

	public Categorie(int idcategorie, String titre, String visuel) {
		super();
		this.idcategorie = idcategorie;
		this.titre = titre;
		this.visuel = visuel;
	}

	public Categorie() {

	}

	public int getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
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
		if (idcategorie != other.idcategorie)
			return false;
		return true;
	}

}
