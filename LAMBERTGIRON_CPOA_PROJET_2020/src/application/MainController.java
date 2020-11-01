package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;
import modele.metier.Client;
import modele.metier.Commande;
import modele.metier.LigneCommande;
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
	private Button detailButton;

	@FXML
	private Button detailComButton;

	@FXML
	private Button validPers;

	@FXML
	private Button effaceFilter;

	@FXML
	private ChoiceBox<String> choixPers;

	@FXML
	private TabPane tabpane;

	@FXML
	private Tab tabComm;

	@FXML
	private TextField filterField;

	@FXML
	private TextField filterTarif;

	@FXML
	private Label lblTarif;

	@FXML
	private Label lblEuros;

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
	TableColumn<Client, String> IdentClient;

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
	TableView<Commande> tableCommande = new TableView<Commande>();

	@FXML
	TableColumn<Commande, Integer> idCommande;

	@FXML
	TableColumn<Commande, LocalDate> dateCommande;

	@FXML
	TableColumn<Commande, Integer> idClient;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			URL fxmlURL = getClass().getResource("/fxml/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 755.0, 518.0);
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

		this.detailButton.setDisable(true);
		this.modifButton.setDisable(true);
		this.supprButton.setDisable(true);
		this.detailComButton.setDisable(true);
		this.lblTarif.setVisible(false);
		this.filterTarif.setVisible(false);
		this.filterField.setDisable(true);
		this.effaceFilter.setDisable(true);
		this.lblEuros.setVisible(false);

		// CHOIX DE LA PERSISTANCE
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

		// PARAMETRAGE TABLE CATEGORIE
		IDCateg.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("idcategorie"));
		TitreCateg.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
		VisuCateg.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

		this.tableCateg.getColumns().setAll(IDCateg, TitreCateg, VisuCateg);

		// PARAMETRAGE TABLE CLIENT
		idClients.setCellValueFactory(new PropertyValueFactory<Client, Integer>("idclient"));
		nomClients.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		prenomClients.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		IdentClient.setCellValueFactory(new PropertyValueFactory<Client, String>("identifiant"));
		villeClients.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));

		this.tableClients.getColumns().setAll(idClients, nomClients, prenomClients, IdentClient, villeClients);

		// PARAMETRAGE TABLE PRODUIT
		idProduit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idproduit"));
		nomProduit.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		tarifProduit.setCellValueFactory(new PropertyValueFactory<Produit, Float>("tarif"));
		idCategProduit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idcategorie"));
		// nbCom.setCellValueFactory(new PropertyValueFactory<Produit,
		// Integer>("quantite"));

		this.tableProduit.getColumns().setAll(idProduit, nomProduit, tarifProduit, idCategProduit);

		// PARAMETRAGE TABLE COMMANDE
		idCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idcom"));
		dateCommande.setCellValueFactory(new PropertyValueFactory<Commande, LocalDate>("datecom"));
		idClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idcli"));

		this.tableCommande.getColumns().setAll(idCommande, dateCommande, idClient);

		tableUpdate();

	}

	@SuppressWarnings("unchecked")
	public void tableUpdate() throws Exception {

		// AFFICHAGE TABLE CATEGORIE
		this.tableCateg.getItems().setAll(dao.getCategorieDAO().findAll());

		// AFFICHAGE TABLE CLIENT + FILTRAGE
		ObservableList<Client> dataListCli = FXCollections.observableArrayList();
		dataListCli.addAll(dao.getClientDAO().findAll());
		FilteredList<Client> filteredDataCli = new FilteredList<>(dataListCli, b -> true);
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataCli.setPredicate(Client -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Client.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (Client.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
		});

		SortedList<Client> sortedDataCli = new SortedList<>(filteredDataCli);
		sortedDataCli.comparatorProperty().bind(tableClients.comparatorProperty());
		this.tableClients.setItems(sortedDataCli);
		this.tableClients.getSortOrder().addAll(nomClients);

		// AFFICHAGE TABLE PRODUIT + FILTRAGE
		ObservableList<Produit> dataListProd = FXCollections.observableArrayList();
		dataListProd.addAll(dao.getProduitDAO().findAll());
		FilteredList<Produit> filteredDataProd = new FilteredList<>(dataListProd, b -> true);
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataProd.setPredicate(Produit -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(Produit.getIdcategorie()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});

		filterTarif.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataProd.setPredicate(Produit -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				if (Produit.getTarif() < Float.valueOf(newValue)) {
					return true;
				} else
					return false;
			});
		});

		SortedList<Produit> sortedDataProd = new SortedList<>(filteredDataProd);
		sortedDataProd.comparatorProperty().bind(tableProduit.comparatorProperty());
		this.tableProduit.setItems(sortedDataProd);

		// AFFICHAGE TABLE COMMANDE + FILTRAGE
		ObservableList<Commande> dataListCom = FXCollections.observableArrayList();
		dataListCom.addAll(dao.getCommandeDAO().findAll());
		FilteredList<Commande> filteredDataCom = new FilteredList<>(dataListCom, b -> true);
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataCom.setPredicate(Commande -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(Commande.getIdcli()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});

		SortedList<Commande> sortedDataCom = new SortedList<>(filteredDataCom);
		sortedDataCom.comparatorProperty().bind(tableCommande.comparatorProperty());
		this.tableCommande.setItems(sortedDataCom);
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
	public void selectParam() {
		if (this.tableCateg.isFocused() == true) {
			this.detailButton.setDisable(false);
			this.modifButton.setDisable(false);
			this.supprButton.setDisable(false);
		} else if (this.tableClients.isFocused() == true) {
			this.detailButton.setDisable(false);
			this.modifButton.setDisable(false);
			this.supprButton.setDisable(false);
			this.detailComButton.setDisable(false);
		} else if (this.tableCommande.isFocused() == true) {
			this.detailButton.setDisable(false);
			this.modifButton.setDisable(false);
			this.supprButton.setDisable(false);
		} else if (this.tableProduit.isFocused() == true) {
			this.detailButton.setDisable(false);
			this.modifButton.setDisable(false);
			this.supprButton.setDisable(false);
		}
	}

	@FXML
	public void deselectParam() {
		this.detailButton.setDisable(true);
		this.modifButton.setDisable(true);
		this.supprButton.setDisable(true);
		this.detailComButton.setDisable(true);
	}

	@FXML
	public void affiFiltre() {
		this.filterField.setDisable(false);
		this.lblTarif.setVisible(true);
		this.lblEuros.setVisible(true);
		this.filterTarif.setVisible(true);
	}

	@FXML
	public void dissFiltre() {
		this.filterField.setDisable(false);
		this.lblTarif.setVisible(false);
		this.lblEuros.setVisible(false);
		this.filterTarif.setVisible(false);
	}

	@FXML
	public void creer(ActionEvent event) throws Exception {

		int idTab = tabpane.getSelectionModel().getSelectedIndex();

		FXMLLoader fxmlLoader;
		Stage stage = new Stage();
		stage.setResizable(false);
		EnumAction action = EnumAction.Create;
		MainController main = this;

		switch (idTab) {
		case 0:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCategorie.fxml"));

			new CategorieController().create(fxmlLoader, dao, stage, main, action);
			break;

		case 1:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutClient.fxml"));

			new ClientController().create(fxmlLoader, dao, stage, main, action);
			break;

		case 2:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutProduit.fxml"));

			new ProduitController().create(fxmlLoader, dao, stage, main, action);

			break;

		case 3:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCommande.fxml"));

			new CommandeController().create(fxmlLoader, dao, stage, main, action);

			break;
		}
		this.filterField.setText("");
		this.filterTarif.setText("");
		this.effaceFilter.setDisable(true);

	}

	@FXML
	public void modif(ActionEvent event) throws Exception {

		int idTab = tabpane.getSelectionModel().getSelectedIndex();

		FXMLLoader fxmlLoader;
		Stage stage = new Stage();
		EnumAction action = EnumAction.Update;
		MainController main = this;

		switch (idTab) {
		case 0:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCategorie.fxml"));

			Categorie categ = this.tableCateg.getSelectionModel().getSelectedItem();
			new CategorieController().update(fxmlLoader, dao, stage, main, action, categ);
			break;

		case 1:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutClient.fxml"));

			Client cli = this.tableClients.getSelectionModel().getSelectedItem();
			new ClientController().update(fxmlLoader, dao, stage, main, action, cli);
			break;

		case 2:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutProduit.fxml"));

			Produit prod = this.tableProduit.getSelectionModel().getSelectedItem();
			new ProduitController().update(fxmlLoader, dao, stage, main, action, prod);
			break;

		case 3:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCommande.fxml"));

			Commande com = this.tableCommande.getSelectionModel().getSelectedItem();
			new CommandeController().update(fxmlLoader, dao, stage, main, action, com);
			break;
		}
		this.filterField.setText("");
		this.filterTarif.setText("");
		this.effaceFilter.setDisable(true);

	}

	@FXML
	public void suppr(ActionEvent event) throws Exception {

		int idTab = tabpane.getSelectionModel().getSelectedIndex();

		switch (idTab) {
		case 0:
			Categorie categ = this.tableCateg.getSelectionModel().getSelectedItem();

			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Suppression d'une categorie");
			alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ? ");
			alert.setContentText("Categorie : " + categ.getTitre());
			ButtonType accept = new ButtonType("Oui");
			ButtonType refus = new ButtonType("Non");
			alert.getButtonTypes().setAll(accept, refus);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == accept) {
				dao.getCategorieDAO().delete(categ);
				tableUpdate();
				alert.close();
			} else
				alert.close();

			break;

		case 1:
			Client cli = this.tableClients.getSelectionModel().getSelectedItem();

			Alert alert1 = new Alert(Alert.AlertType.WARNING);
			alert1.setTitle("Suppression d'un client");
			alert1.setHeaderText("Voulez-vous vraiment supprimer ce client ? ");
			alert1.setContentText("Client : " + cli.getNom() + " " + cli.getPrenom());
			ButtonType accept1 = new ButtonType("Oui");
			ButtonType refus1 = new ButtonType("Non");
			alert1.getButtonTypes().setAll(accept1, refus1);
			Optional<ButtonType> result1 = alert1.showAndWait();
			if (result1.get() == accept1) {
				dao.getClientDAO().delete(cli);
				tableUpdate();
				alert1.close();
			} else
				alert1.close();
			break;

		case 2:
			Produit prod = this.tableProduit.getSelectionModel().getSelectedItem();

			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setTitle("Suppression d'un produit");
			alert2.setHeaderText("Voulez-vous vraiment supprimer ce produit ? ");
			alert2.setContentText("Produit : " + prod.getNom() + " Prix : " + prod.getTarif() + " .€");
			ButtonType accept2 = new ButtonType("Oui");
			ButtonType refus2 = new ButtonType("Non");
			alert2.getButtonTypes().setAll(accept2, refus2);
			Optional<ButtonType> result2 = alert2.showAndWait();
			if (result2.get() == accept2) {
				dao.getProduitDAO().delete(prod);
				tableUpdate();
				alert2.close();
			} else
				alert2.close();
			break;

		case 3:
			Commande com = this.tableCommande.getSelectionModel().getSelectedItem();

			Client idclient = dao.getClientDAO().getById(com.getIdcli());

			Alert alert3 = new Alert(Alert.AlertType.WARNING);
			alert3.setTitle("Suppression d'une commande");
			alert3.setHeaderText("Voulez-vous vraiment supprimer cette commande ainsi que ses lignes de commande ? ");
			alert3.setContentText("Id de la commande : " + com.getIdcom() + " a la date : " + com.getDatecom()
					+ " du client : " + idclient.getNom() + " " + idclient.getPrenom());
			ButtonType accept3 = new ButtonType("Oui");
			ButtonType refus3 = new ButtonType("Non");
			alert3.getButtonTypes().setAll(accept3, refus3);
			Optional<ButtonType> result3 = alert3.showAndWait();
			if (result3.get() == accept3) {

				LigneCommande lc = new LigneCommande();
				lc.setIdcom(com.getIdcom());
				dao.getCommandeDAO().delete(com);
				for (LigneCommande lc2 : dao.getLigneCommandeDAO().findAll()) {
					if (lc2.getIdcom() == com.getIdcom()) {
						lc.setIdprod(lc2.getIdprod());
						dao.getLigneCommandeDAO().delete(lc);

					}
				}
				tableUpdate();
				alert3.close();
			} else
				alert3.close();
			break;
		}
		this.detailButton.setDisable(true);
		this.modifButton.setDisable(true);
		this.supprButton.setDisable(true);
		this.filterField.setText("");
		this.filterTarif.setText("");
		this.effaceFilter.setDisable(true);

	}

	@FXML
	public void close(ActionEvent event) throws Exception {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void detailSelect() throws Exception {

		int idTab = tabpane.getSelectionModel().getSelectedIndex();

		FXMLLoader fxmlLoader;
		Stage stage = new Stage();
		EnumAction action = EnumAction.Visualisation;
		MainController main = this;

		switch (idTab) {
		case 0:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCategorie.fxml"));

			Categorie categ = this.tableCateg.getSelectionModel().getSelectedItem();
			new CategorieController().visualisation(fxmlLoader, dao, stage, main, action, categ);
			break;

		case 1:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutClient.fxml"));

			Client cli = this.tableClients.getSelectionModel().getSelectedItem();
			new ClientController().visualisation(fxmlLoader, dao, stage, main, action, cli);
			break;

		case 2:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutProduit.fxml"));

			Produit prod = this.tableProduit.getSelectionModel().getSelectedItem();
			new ProduitController().visualisation(fxmlLoader, dao, stage, main, action, prod);
			break;

		case 3:
			fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajoutCommande.fxml"));

			Commande com = this.tableCommande.getSelectionModel().getSelectedItem();
			new CommandeController().visualisation(fxmlLoader, dao, stage, main, action, com);
			break;
		}
	}

	@FXML
	public void detailComCli() throws Exception {

		Client cli = this.tableClients.getSelectionModel().getSelectedItem();

		SingleSelectionModel<Tab> tabCom = tabpane.getSelectionModel();
		tabCom.select(3);

		this.filterField.setText(String.valueOf(cli.getIdclient()));

		this.detailButton.setDisable(true);
		this.modifButton.setDisable(true);
		this.supprButton.setDisable(true);
		this.detailComButton.setDisable(true);

	}

	@FXML
	public void effaceFiltre() {
		this.filterField.setText("");
		this.filterTarif.setText("");
		this.effaceFilter.setDisable(true);
	}

	@FXML
	public void changeEtatButton() {
		this.effaceFilter.setDisable(false);
	}
}
