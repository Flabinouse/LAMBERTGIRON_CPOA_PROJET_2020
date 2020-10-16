package modele.connexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Connexion {

	private static Connexion instance;

	private String url, login, pwd;
	private Connection maConnexion;

	private Connexion() throws SQLException, InvalidPropertiesFormatException, IOException {
		this.readFile();
	}

	public static Connexion getInstance() throws SQLException, InvalidPropertiesFormatException, IOException {
		if (instance == null) {
			instance = new Connexion();
		}
		return instance;
	}

	public void readFile() throws InvalidPropertiesFormatException, IOException {

		Properties accesBdd = new Properties();
		File fBdd = new File("bdd.properties");
		FileInputStream source = new FileInputStream(fBdd);
		accesBdd.loadFromXML(source);

		this.url = "jdbc:mysql://";
		String adresse = accesBdd.getProperty("adresse_ip");
		this.login = accesBdd.getProperty("login");
		this.pwd = accesBdd.getProperty("pass");
		String port = accesBdd.getProperty("port");
		String bdd = accesBdd.getProperty("bdd");
		this.url += (adresse + ':' + port + '/' + bdd + "?serverTimezone=Europe/Paris");

	}

	public Connection creeConnexion() throws SQLException {

		if (maConnexion == null || maConnexion.isClosed()) {
			maConnexion = DriverManager.getConnection(this.url, this.login, this.pwd);
		}

		return maConnexion;
	}

}
