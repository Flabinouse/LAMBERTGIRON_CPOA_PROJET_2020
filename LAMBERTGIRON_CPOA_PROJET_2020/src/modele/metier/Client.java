package modele.metier;

public class Client {
	private int idclient;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private String numero;
	private String rue;
	private String postal;
	private String ville;
	private String pays;

	public Client(int idclient, String nom, String prenom, String identifiant, String mdp, String numero, String rue,
			String postal, String ville, String pays) {
		super();
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.numero = numero;
		this.rue = rue;
		this.postal = postal;
		this.ville = ville;
		this.pays = pays;
	}

	public Client() {

	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		if (prenom == null || prenom.trim().isEmpty()) {
			throw new IllegalArgumentException("Le prenom est vide !");
		} else {
			this.prenom = prenom;
		}
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		if (identifiant == null || identifiant.trim().isEmpty()) {
			throw new IllegalArgumentException("L'identifiant est vide !");
		} else {
			this.identifiant = identifiant;
		}
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		if (mdp == null || mdp.trim().isEmpty()) {
			throw new IllegalArgumentException("Le mot de passe est vide !");
		} else {
			this.mdp = mdp;
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero == null || numero.trim().isEmpty()) {
			throw new IllegalArgumentException("Le numero est vide !");
		} else {
			this.numero = numero;
		}
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		if (rue == null || rue.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom de la rue est vide !");
		} else {
			this.rue = rue;
		}
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		if (postal == null || postal.trim().isEmpty()) {
			throw new IllegalArgumentException("Le code postal est vide !");
		} else {
			this.postal = postal;
		}
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		if (ville == null || ville.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom de la ville est vide !");
		} else {
			this.ville = ville;
		}
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		if (pays == null || pays.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom du pays est vide !");
		} else {
			this.pays = pays;
		}
	}

	@Override
	public String toString() {
		return nom + " " + prenom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (idclient != other.idclient)
			return false;
		return true;
	}

}
