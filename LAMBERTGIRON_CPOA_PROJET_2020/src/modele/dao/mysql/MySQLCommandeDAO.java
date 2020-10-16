package modele.dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import modele.connexion.Connexion;
import modele.dao.CommandeDAO;
import modele.metier.Commande;

public class MySQLCommandeDAO implements CommandeDAO {

	private static MySQLCommandeDAO instance;

	public MySQLCommandeDAO() {
	}

	public static MySQLCommandeDAO getInstance() {

		if (instance == null) {
			instance = new MySQLCommandeDAO();
		}

		return instance;
	}

	@Override
	public Commande getById(int idCommande) throws SQLException, InvalidPropertiesFormatException, IOException {
		LocalDate datecom = null;
		int idclient = 0;
		Commande com = null;
		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_commande=?");
		requete.setInt(1, idCommande);
		ResultSet res = requete.executeQuery();
		while (res.next()) {
			datecom = res.getDate("date_commande").toLocalDate();
			idclient = res.getInt("id_client");
			com = new Commande(idCommande, datecom, idclient);
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

		return com;
	}

	@Override
	public boolean create(Commande com) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO Commande (id_commande, date_commande, id_client) " + " VALUES (default,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		requete.setDate(1, java.sql.Date.valueOf(com.getDatecom()));
		requete.setInt(2, com.getIdcli());
		requete.executeUpdate();

		if(check == 0)
			verif = true;

		ResultSet res = requete.getGeneratedKeys();

		if (res.next()) {
			int id = res.getInt(1);
			com.setIdcom(id);
		}

		if (requete != null)
			requete.close();
		if (laConnexion != null)
			laConnexion.close();

		return verif;

	}

	@Override
	public boolean update(Commande com) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("UPDATE Commande SET date_commande=?, id_client=? WHERE id_commande=?");
		requete.setDate(1, java.sql.Date.valueOf(com.getDatecom()));
		requete.setInt(2, com.getIdcli());
		requete.setInt(3, com.getIdcom());
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
	public boolean delete(Commande com) throws SQLException, InvalidPropertiesFormatException, IOException {

		boolean verif = false;
		int check = 0;

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Commande WHERE id_commande=?");
		requete.setInt(1, com.getIdcom());
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
	public ArrayList<Commande> findAll() throws SQLException, InvalidPropertiesFormatException, IOException {

		ArrayList<Commande> lcom = new ArrayList<Commande>();

		Connection laConnexion = Connexion.getInstance().creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			int id_com = res.getInt("id_commande");
			LocalDate datecom = res.getDate("date_commande").toLocalDate();
			int id_cli = res.getInt("id_client");
			Commande concat = new Commande(id_com, datecom, id_cli);

			lcom.add(concat);
		}

		return lcom;
	}
}