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
import modele.dao.CategorieDAO;
import modele.metier.Categorie;

public class MySQLCategorieDAO implements CategorieDAO {

	private static MySQLCategorieDAO instance;

	private MySQLCategorieDAO() {
	}

	public static MySQLCategorieDAO getInstance() {

		if (instance == null) {
			instance = new MySQLCategorieDAO();
		}

		return instance;
	}

	@Override
	public boolean create(Categorie categ) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO Categorie (id_categorie, titre, visuel)" + " VALUES (default,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		requete.setString(1, categ.getTitre());
		requete.setString(2, categ.getVisuel());
		check = requete.executeUpdate();

		if(check == 0)
			verif = true;

		ResultSet res = requete.getGeneratedKeys();

		if (res.next()) {
			int id = res.getInt(1);
			categ.setIdcategorie(id);
		}

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();
		return verif;
	}

	@Override
	public boolean delete(Categorie categ) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Categorie WHERE id_categorie=?");
		requete.setInt(1, categ.getIdcategorie());
		requete.executeUpdate();
		
		if(check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();
		return verif;
	}

	@Override
	public boolean update(Categorie categ) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("UPDATE Categorie SET titre=?, visuel=? WHERE id_categorie =?");
		requete.setString(1, categ.getTitre());
		requete.setString(2, categ.getVisuel());
		requete.setInt(3, categ.getIdcategorie());
		requete.executeUpdate();

		if(check == 0)
			verif = true;

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();
		return verif;
	}

	@Override
	public Categorie getById(int id_categorie) throws SQLException, InvalidPropertiesFormatException, IOException {

		String titre = "";
		String visuel = "";
		Categorie categ = null;
		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie where id_categorie=?");
		requete.setInt(1, id_categorie);
		ResultSet res = requete.executeQuery();
		while (res.next()) {
			titre = res.getString("titre");
			visuel = res.getString("visuel");
			categ = new Categorie(id_categorie, titre, visuel);
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
		return categ;

	}

	@Override
	public ArrayList<Categorie> findAll() throws SQLException, InvalidPropertiesFormatException, IOException {

		ArrayList<Categorie> lcateg = new ArrayList<Categorie>();

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			int id_categ = res.getInt("id_categorie");
			String titre = res.getString("titre");
			String visuel = res.getString("visuel");
			Categorie concat = new Categorie(id_categ, titre, visuel);

			lcateg.add(concat);
		}
		return lcateg;
	}
}
