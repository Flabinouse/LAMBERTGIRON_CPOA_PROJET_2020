package modele.dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import modele.connexion.Connexion;
import modele.dao.ProduitDAO;
import modele.metier.Produit;

public class MySQLProduitDAO implements ProduitDAO {

	private static MySQLProduitDAO instance;

	public MySQLProduitDAO() {
	}

	public static MySQLProduitDAO getInstance() {

		if (instance == null) {
			instance = new MySQLProduitDAO();
		}

		return instance;
	}

	@Override
	public Produit getById(int idproduit) throws SQLException, InvalidPropertiesFormatException, IOException {
		String nom = "";
		String description = "";
		float tarif = 0;
		String visuel = "";
		int idcateg = 0;
		Produit prod = null;
		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE id_produit=?");
		requete.setInt(1, idproduit);
		ResultSet res = requete.executeQuery();
		while (res.next()) {
			nom = res.getString("nom");
			description = res.getString("description");
			tarif = res.getFloat("tarif");
			visuel = res.getString("visuel");
			idcateg = res.getInt("id_categorie");
			prod = new Produit(idproduit, nom, description, tarif, visuel, idcateg);
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

		return prod;
	}

	@Override
	public boolean create(Produit prod) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("INSERT INTO Produit (id_produit, nom, description, tarif, visuel, id_categorie)"
						+ " VALUES (default,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		requete.setString(1, prod.getNom());
		requete.setString(2, prod.getDescription());
		requete.setFloat(3, prod.getTarif());
		requete.setString(4, prod.getVisuel());
		requete.setInt(5, prod.getIdcategorie());
		requete.executeUpdate();

		if (check == 0)
			verif = true;

		ResultSet res = requete.getGeneratedKeys();

		if (res.next()) {
			int id = res.getInt(1);
			prod.setIdproduit(id);
		}

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;
	}

	@Override
	public boolean update(Produit prod) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Produit SET nom=?, description=?, tarif=?, visuel=?, id_categorie=? WHERE id_produit=?");
		requete.setString(1, prod.getNom());
		requete.setString(2, prod.getDescription());
		requete.setFloat(3, prod.getTarif());
		requete.setString(4, prod.getVisuel());
		requete.setInt(5, prod.getIdcategorie());
		requete.setInt(6, prod.getIdproduit());
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
	public boolean delete(Produit prod) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Produit WHERE id_produit=?");
		requete.setInt(1, prod.getIdproduit());
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
	public ArrayList<Produit> findAll() throws SQLException, InvalidPropertiesFormatException, IOException {

		ArrayList<Produit> lprod = new ArrayList<Produit>();

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			int idprod = res.getInt("id_produit");
			String nom = res.getString("nom");
			String description = res.getString("description");
			float tarif = res.getFloat("tarif");
			String visuel = res.getString("visuel");
			int idcateg = res.getInt("id_categorie");
			Produit concat = new Produit(idprod, nom, description, tarif, visuel, idcateg);

			lprod.add(concat);
		}

		return lprod;
	}
}
