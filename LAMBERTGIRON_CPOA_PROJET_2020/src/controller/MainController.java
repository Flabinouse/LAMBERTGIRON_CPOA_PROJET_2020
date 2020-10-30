package controller;

import java.net.URL;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;
import modele.metier.Client;
import modele.metier.Produit;

public class MainController extends Application {
	private DAOFactory dao;

	@FXML
	private Button creerButton;

	@FXML
	private Button modifButton;

	@FXML
	private Button supprButton;

	@FXML
	private Button closeButton;

	@FXML
	private ChoiceBox<String> choixPers;

	@FXML
	private Button validPers;

	@FXML
	private TabPane tabpane;

	@FXML
	TableView<Categorie> tableCateg = new TableView<Categorie>();

	@FXML
	TableColumn<Categorie, Integer> IDCateg;

	@FXML
	TableColumn<Categorie, String> TitreCateg;

	@FXML
	TableColumn<Categorie, String> VisuCateg;

	@FXML
	TableView<Client> tableClients = new TableView<Client>();

	@FXML
	TableColumn<Client, Integer> idClients;

	@FXML
	TableColumn<Client, String> nomClients;

	@FXML
	TableColumn<Client, String> prenomClients;

	@FXML
	TableColumn<Client, String> villeClients;

	@FXML
	TableView<Produit> tableProduit = new TableView<Produit>();

	@FXML
	TableColumn<Produit, Integer> idProduit;

	@FXML
	TableColumn<Produit, String> nomProduit;

	@FXML
	TableColumn<Produit, Float> tarifProduit;

	@FXML
	TableColumn<Produit, Integer> idCategProduit;

	@FXML
	TableColumn<Produit, Integer> nbCom;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			URL fxmlURL = getClass().getResource("/fxml/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 755.0, 475.0);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Application de gestion des pulls de noel");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() throws Exception {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logiciel de gestion de pulls de noel");
		alert.setHeaderText("Bienvenue");
		alert.setContentText("Veuillez choisir un mode :");
		ButtonType bMySql = new ButtonType("MySQL");
		ButtonType bListeMemoire = new ButtonType("Liste mémoire");
		alert.getButtonTypes().setAll(bMySql, bListeMemoire);

		Optional<ButtonType> result = alert.showAndWait();
		ObservableList<String> listPersi = FXCollections.observableArrayList("MySQL", "Liste Memoire");
		this.choixPers.setItems(listPersi);

		if (result.get() == bMySql) {
			dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
			this.choixPers.setValue("MySQL");
		} else if (result.get() == bListeMemoire) {
			dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
			this.choixPers.setValue("Liste Memoire");
		} else {
			alert.close();
		}

		IDCateg.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcategorie"));
		TitreCateg.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
		VisuCateg.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

		this.tableCateg.getColumns().setAll(IDCateg, TitreCateg, VisuCateg);
		// this.tableCateg.getItems().addAll(dao.getCategorieDAO().findAll());

		idClients.setCellValueFactory(new PropertyValueFactory<Client, Integer>("idclient"));
		nomClients.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		prenomClients.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		villeClients.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));

		this.tableClients.getColumns().setAll(idClients, nomClients, prenomClients, villeClients);
		// this.tableClients.getItems().addAll(dao.getClientDAO().findAll());

		idProduit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idproduit"));
		nomProduit.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		tarifProduit.setCellValueFactory(new PropertyValueFactory<Produit, Float>("tarif"));
		idCategProduit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idcategorie"));

		this.tableProduit.getColumns().setAll(idProduit, nomProduit, tarifProduit, idCategProduit);
		// this.tableProduit.getItems().addAll(dao.getProduitDAO().findAll());

		tableUpdate();

	}

	public void tableUpdate() throws Exception {
		this.tableCateg.getItems().setAll(dao.getCategorieDAO().findAll());
		this.tableClients.getItems().setAll(dao.getClientDAO().findAll());
		this.tableProduit.getItems().setAll(dao.getProduitDAO().findAll());
	}

	@FXML
	public void validPersistance(ActionEvent event) throws Exception {
		if (this.choixPers.getValue() == "MySQL") {
			dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
			this.choixPers.setValue("MySQL");
			tableUpdate();
		} else {
			dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
			this.choixPers.setValue("Liste Memoire");
			tableUpdate();
		}
	}

	@FXML
	public void creerCateg(ActionEvent event) throws Exception {

		try {
			int idTab = tabpane.getSelectionModel().getSelectedIndex();

			switch (idTab) {
			case 0:
				URL fxmlURL = getClass().getResource("/fxml/ajoutCategorie.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
				Node root = fxmlLoader.load();
				Scene scene = new Scene((VBox) root, 400, 240);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setTitle("Ajout d'une categorie");
				stage.setResizable(false);
				stage.show();
				tableUpdate();
				break;

			case 1:
				URL fxmlcliURL = getClass().getResource("/fxml/ajoutClient.fxml");
				FXMLLoader fxmlcliLoader = new FXMLLoader(fxmlcliURL);
				Node rootcli = fxmlcliLoader.load();
				Scene scenecli = new Scene((VBox) rootcli, 500, 550);
				Stage stagecli = new Stage();
				stagecli.initModality(Modality.APPLICATION_MODAL);
				stagecli.setScene(scenecli);
				stagecli.setTitle("Ajout d'un client");
				stagecli.setResizable(false);
				stagecli.show();
				tableUpdate();
				break;
				
			case 2:
				URL fxmlprodURL = getClass().getResource("/fxml/ajoutProduit.fxml");
				FXMLLoader fxmlprodLoader = new FXMLLoader(fxmlprodURL);
				Node rootprod = fxmlprodLoader.load();
				Scene sceneprod = new Scene((VBox) rootprod, 500, 400);
				Stage stageprod = new Stage();
				stageprod.initModality(Modality.APPLICATION_MODAL);
				stageprod.setScene(sceneprod);
				stageprod.setTitle("Ajout d'un produit");
				stageprod.setResizable(false);
				stageprod.show();
				tableUpdate();
				break;
				
			case 3:
				URL fxmlcomURL = getClass().getResource("/fxml/ajoutCommandefxml");
				FXMLLoader fxmlcomLoader = new FXMLLoader(fxmlcomURL);
				Node rootcom = fxmlcomLoader.load();
				Scene scenecom = new Scene((VBox) rootcom, 400, 240);
				Stage stagecom = new Stage();
				stagecom.initModality(Modality.APPLICATION_MODAL);
				stagecom.setScene(scenecom);
				stagecom.setTitle("Ajout d'une commande");
				stagecom.setResizable(false);
				stagecom.show();
				tableUpdate();
				break;
			}
		} catch (IllegalArgumentException e) {

		}
	}

	@FXML
	public void modifCateg(ActionEvent event) throws Exception {

		try {
			Categorie categ = this.tableCateg.getSelectionModel().getSelectedItem();
			try {
				URL fxmlURL = getClass().getResource("/fxml/ajoutCategorie.fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
				Node root = fxmlLoader.load();
				Scene scene = new Scene((VBox) root, 400, 240);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setTitle("Ajout d'une categorie");
				stage.setResizable(false);
				stage.show();
				tableUpdate();
			} catch (IllegalArgumentException e) {

			}

		} catch (IllegalArgumentException e) {

		}
	}

	@FXML
	public void supprCateg(ActionEvent event) throws Exception {

		try {

			Categorie categ = this.tableCateg.getSelectionModel().getSelectedItem();
			dao.getCategorieDAO().delete(categ);
			tableUpdate();
		} catch (IllegalArgumentException e) {

		}
	}

	@FXML
	public void closeCateg(ActionEvent event) throws Exception {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
