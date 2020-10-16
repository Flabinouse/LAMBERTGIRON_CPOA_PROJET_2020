package modele.dao;

import modele.metier.LigneCommande;

public interface LigneCommandeDAO extends IDAO<LigneCommande> {

	public abstract LigneCommande getById(int idcom, int idprod) throws Exception;

}
