package modele.dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import modele.connexion.Connexion;
import modele.dao.LigneCommandeDAO;
import modele.metier.LigneCommande;

public class MySQLLigneCommandeDAO implements LigneCommandeDAO {

	private static MySQLLigneCommandeDAO instance;

	public MySQLLigneCommandeDAO() {
	}

	public static MySQLLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new MySQLLigneCommandeDAO();
		}

		return instance;
	}

	@Override
	public LigneCommande getById(int idLigneCom, int idprod)
			throws SQLException, InvalidPropertiesFormatException, IOException {

		int quantite = 0;
		double tarif = 0;
		LigneCommande ligncom = null;
		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("SELECT * FROM Ligne_commande where id_commande=? AND id_produit=?");
		requete.setInt(1, idLigneCom);
		requete.setInt(2, idprod);
		ResultSet res = requete.executeQuery();
		while (res.next()) {
			quantite = res.getInt("quantite");
			tarif = res.getDouble("tarif_unitaire");
			ligncom = new LigneCommande(idLigneCom, idprod, quantite, tarif);
		}

		if (res != null) {
			res.close();
		}
		if (requete != null) {
			requete.close();
		}
		if (laConnexion != null) {
			laConnexion.close();
		}
		return ligncom;
	}

	@Override
	public boolean create(LigneCommande ligncom) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("INSERT INTO Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) "
						+ " VALUES (?,?,?,?)");
		requete.setInt(1, ligncom.getIdcom());
		requete.setInt(2, ligncom.getIdprod());
		requete.setInt(3, ligncom.getQuantite());
		requete.setDouble(4, ligncom.getTarifunit());
		requete.executeUpdate();

		if (check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public boolean update(LigneCommande ligncom) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Ligne_commande SET quantite=?,tarif_unitaire=? WHERE id_commande=? AND id_produit=?");
		requete.setInt(1, ligncom.getQuantite());
		requete.setDouble(2, ligncom.getTarifunit());
		requete.setInt(3, ligncom.getIdcom());
		requete.setInt(4, ligncom.getIdprod());
		requete.executeUpdate();

		if (check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public boolean delete(LigneCommande ligncom) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("DELETE FROM Ligne_commande WHERE id_commande=? AND id_produit=?");
		requete.setInt(1, ligncom.getIdcom());
		requete.setInt(2, ligncom.getIdprod());
		requete.executeUpdate();

		if (check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public ArrayList<LigneCommande> findAll() throws SQLException, InvalidPropertiesFormatException, IOException {

		ArrayList<LigneCommande> lligncom = new ArrayList<LigneCommande>();

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Ligne_commande");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			int idcom = res.getInt("id_commande");
			int idprod = res.getInt("id_produit");
			int quantite = res.getInt("quantite");
			double tarif = res.getDouble("tarif_unitaire");
			LigneCommande concat = new LigneCommande(idcom, idprod, quantite, tarif);
			lligncom.add(concat);
		}
		return lligncom;
	}

	@Override
	public LigneCommande getById(int id) throws Exception {

		/*
		 * int quantite = 0; double tarif = 0; int idprod = 0; LigneCommande ligncom =
		 * null; Connection laConnexion = Connexion.getInstance().creeConnexion();
		 * PreparedStatement requete = laConnexion.
		 * prepareStatement("SELECT * FROM Ligne_commande where id_commande=?");
		 * requete.setInt(1, id); ResultSet res = requete.executeQuery(); while
		 * (res.next()) { quantite = res.getInt("quantite"); tarif =
		 * res.getDouble("tarif_unitaire"); idprod = res.getInt("id_produit"); ligncom =
		 * new LigneCommande(id, idprod, quantite, tarif); }
		 * 
		 * if (res != null) { res.close(); } if (requete != null) { requete.close(); }
		 * if (laConnexion != null) { laConnexion.close(); } return ligncom;
		 */
		return null;
	}
}
