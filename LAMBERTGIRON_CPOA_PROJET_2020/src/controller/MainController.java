package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.dao.factory.DAOFactory;
import modele.dao.factory.Persistance;
import modele.metier.Categorie;
import modele.metier.Client;
import modele.metier.LigneCommande;
import modele.metier.Produit;

public class MainController {
	private DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	@FXML private Button creerButton;	
	
	@FXML private Button modifButton;	
	
	@FXML private Button supprButton;	
	
	@FXML private Button closeButton;	
	
	@FXML TableView<Categorie> tableCateg = new TableView<Categorie>();
	
	@FXML TableColumn<Categorie,Integer> IDCateg;
	
	@FXML TableColumn<Categorie,String> TitreCateg;

	@FXML TableColumn<Categorie,String> VisuCateg;
	
	@FXML TableView<Client> tableClients = new TableView<Client>();
	
	@FXML TableColumn<Client,Integer> idClients;
	
	@FXML TableColumn<Client,String> nomClients;

	@FXML TableColumn<Client,String> prenomClients;
	
	@FXML TableColumn<Client,String> villeClients;
	
	@FXML TableView<Produit> tableProduit = new TableView<Produit>();
	
	@FXML TableColumn<Produit,Integer> idProduit;
	
	@FXML TableColumn<Produit,String> nomProduit;

	@FXML TableColumn<Produit,Float> tarifProduit;
	
	@FXML TableColumn<Produit,Integer> idCategProduit;
	
	@FXML TableColumn<Produit,Integer> nbCom;
	
	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() throws Exception {
		
		IDCateg.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("idcategorie"));
		
		TitreCateg.setCellValueFactory(new PropertyValueFactory<Categorie,String>("titre"));
		
		VisuCateg.setCellValueFactory(new PropertyValueFactory<Categorie,String>("visuel"));
	
		this.tableCateg.getColumns().setAll(IDCateg,TitreCateg,VisuCateg);
		this.tableCateg.getItems().addAll(dao.getCategorieDAO().findAll());
		
		
		
		idClients.setCellValueFactory(new PropertyValueFactory<Client,Integer>("idclient"));
		
		nomClients.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
		
		prenomClients.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
		
		villeClients.setCellValueFactory(new PropertyValueFactory<Client,String>("ville"));
	
		this.tableClients.getColumns().setAll(idClients,nomClients,prenomClients,villeClients);
		this.tableClients.getItems().addAll(dao.getClientDAO().findAll());
		
		
		idProduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("idproduit"));
		
		nomProduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
		
		tarifProduit.setCellValueFactory(new PropertyValueFactory<Produit,Float>("tarif"));
		
		idCategProduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("idcategorie"));
		
		//nbCom.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
	
		this.tableProduit.getColumns().setAll(idProduit,nomProduit,tarifProduit,idCategProduit);
		this.tableProduit.getItems().addAll(dao.getProduitDAO().findAll());
	
	}
	
	public void recupQantite() throws Exception {
		ObservableList<LigneCommande> list = FXCollections.observableArrayList(dao.getLigneCommandeDAO().findAll());
		for(int i = 0; i < list.size(); i++) {
			
		}
		
	}

	@FXML
	public void creerCateg(ActionEvent event) throws Exception {

		try {
		} catch (IllegalArgumentException e) {

		}
	}
	@FXML
	public void modifCateg(ActionEvent event) throws Exception {

		try {
		} catch (IllegalArgumentException e) {

		}
	}
	@FXML
	public void supprCateg(ActionEvent event) throws Exception {

		try {
		} catch (IllegalArgumentException e) {

		}
	}
	@FXML
	public void closeCateg(ActionEvent event) throws Exception {

		try {
		} catch (IllegalArgumentException e) {

		}
	}

}
