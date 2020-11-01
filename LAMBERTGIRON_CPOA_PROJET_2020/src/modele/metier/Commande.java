package modele.metier;

import java.time.LocalDate;

public class Commande {
	private int idcom;
	private LocalDate datecom;
	private int idcli;

	public Commande(int idcom, LocalDate datecom, int idcli) {
		super();
		this.idcom = idcom;
		this.datecom = datecom;
		this.idcli = idcli;
	}

	public Commande() {

	}

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		this.idcom = idcom;
	}

	public LocalDate getDatecom() {
		return datecom;
	}

	public void setDatecom(LocalDate datecom) {
		if (datecom == null) {
			throw new IllegalArgumentException("La date est null !");
		} else
			this.datecom = datecom;
	}

	public int getIdcli() {
		return idcli;
	}

	public void setIdcli(int idcli) {
		if (idcli <= 0) {
			throw new IllegalArgumentException("L'id client est null ou negatif !");
		} else {
			this.idcli = idcli;
		}
	}

	@Override
	public String toString() {
		return "Commande [idcom=" + idcom + ", datecom=" + datecom + ", idcli=" + idcli + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (idcom != other.idcom)
			return false;
		if (idcli != other.idcli)
			return false;
		return true;
	}

}
