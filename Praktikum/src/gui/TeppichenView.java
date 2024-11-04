package gui;

import business.Teppich;
import business.TeppichenModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class TeppichenView {

	private TeppichenModel model;
	private TeppichControl control;
	private Teppich teppich;

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblKategorie = new Label("Kategorie:");
	private Label lblArtikelnummer = new Label("Artikelnummer:");
	private Label lblBreite = new Label("Breite:");
	private Label lblLange = new Label("Lange:");
	private Label lblFarben = new Label("Farben:");
	private TextField txtKategorie = new TextField();
	private TextField txtArtikelnummer = new TextField();
	private TextField txtBreite = new TextField();
	private TextField txtLange = new TextField();
	private TextField txtFarben = new TextField();
	private TextArea txtAnzeige = new TextArea();
	private Button btnEingabe = new Button("Eingabe");
	private Button btnAnzeige = new Button("Anzeige");
	private MenuBar mnbrMenuLeiste = new MenuBar();
	private Menu mnDatei = new Menu("Datei");
	private MenuItem mnItmCsvImport = new MenuItem("csv-Import");
	private MenuItem mnItmTxtImport = new MenuItem("txt-Import");
	private MenuItem mnItmCsvExport = new MenuItem("csv-Export");
	// -------Ende Attribute der grafischen Oberflaeche-------

	// speichert temporaer ein Objekt vom Typ teppich

	public TeppichenView(Stage primaryStage, TeppichenModel model, TeppichControl control) {
		this.model = model;
		this.control = control;
		Scene scene = new Scene(this.pane, 700, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Verwaltung von Teppichladen");
		primaryStage.show();
		this.initKomponenten();
		this.initListener();
	}

	private void initKomponenten() {
		// Labels
		lblEingabe.setLayoutX(20);
		lblEingabe.setLayoutY(40);
		Font font = new Font("Arial", 24);
		lblEingabe.setFont(font);
		lblEingabe.setStyle("-fx-font-weight: bold;");
		lblAnzeige.setLayoutX(400);
		lblAnzeige.setLayoutY(40);
		lblAnzeige.setFont(font);
		lblAnzeige.setStyle("-fx-font-weight: bold;");
		lblKategorie.setLayoutX(20);
		lblKategorie.setLayoutY(90);
		lblArtikelnummer.setLayoutX(20);
		lblArtikelnummer.setLayoutY(130);
		lblBreite.setLayoutX(20);
		lblBreite.setLayoutY(170);
		lblLange.setLayoutX(20);
		lblLange.setLayoutY(210);
		lblFarben.setLayoutX(20);
		lblFarben.setLayoutY(250);
		pane.getChildren().addAll(lblEingabe, lblAnzeige, lblKategorie, lblArtikelnummer, lblBreite, lblLange,
				lblFarben);

		// Textfelder
		txtKategorie.setLayoutX(170);
		txtKategorie.setLayoutY(90);
		txtKategorie.setPrefWidth(200);
		txtArtikelnummer.setLayoutX(170);
		txtArtikelnummer.setLayoutY(130);
		txtArtikelnummer.setPrefWidth(200);
		txtBreite.setLayoutX(170);
		txtBreite.setLayoutY(170);
		txtBreite.setPrefWidth(200);
		txtLange.setLayoutX(170);
		txtLange.setLayoutY(210);
		txtLange.setPrefWidth(200);
		txtFarben.setLayoutX(170);
		txtFarben.setLayoutY(250);
		txtFarben.setPrefWidth(200);
		pane.getChildren().addAll(txtKategorie, txtArtikelnummer, txtBreite, txtLange, txtFarben);

		// Textbereich
		txtAnzeige.setEditable(false);
		txtAnzeige.setLayoutX(400);
		txtAnzeige.setLayoutY(90);
		txtAnzeige.setPrefWidth(270);
		txtAnzeige.setPrefHeight(185);
		pane.getChildren().add(txtAnzeige);

		// Buttons
		btnEingabe.setLayoutX(20);
		btnEingabe.setLayoutY(290);
		btnAnzeige.setLayoutX(400);
		btnAnzeige.setLayoutY(290);
		pane.getChildren().addAll(btnEingabe, btnAnzeige);

		// Menue
		this.mnbrMenuLeiste.getMenus().add(mnDatei);
		this.mnDatei.getItems().add(mnItmCsvImport);
		this.mnDatei.getItems().add(mnItmTxtImport);
		this.mnDatei.getItems().add(new SeparatorMenuItem());
		this.mnDatei.getItems().add(mnItmCsvExport);
		pane.getChildren().add(mnbrMenuLeiste);
	}

	private void initListener() {
		btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				nehmeTeppichAuf();
			}
		});
		btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeTeppichAn();
			}
		});
		mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				control.leseAusDatei("csv");
			}
		});
		mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				control.leseAusDatei("txt");
			}
		});
		mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				control.schreibeTeppichenInCsvDatei();
			}
		});
	}

	public void zeigeTeppichAn() {
		if (model.getTeppichen() != null) {
			txtAnzeige.setText(model.getTeppichen());
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Teppich aufgenommen!");
		}
	}

	public void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

	public void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

	public TextField getTxtKategorie() {
		return txtKategorie;
	}

	public TextField getTxtArtikelnummer() {
		return txtArtikelnummer;
	}

	public TextField getTxtBreite() {
		return txtBreite;
	}

	public TextField getTxtLange() {
		return txtLange;
	}

	public TextField getTxtFarben() {
		return txtFarben;
	}

	public TextArea getTxtAnzeige() {
		return txtAnzeige;
	}

	public void nehmeTeppichAuf() {
		try {
			this.teppich = new Teppich(getTxtKategorie().getText(), Float.parseFloat(getTxtArtikelnummer().getText()),
					Float.parseFloat(getTxtBreite().getText()), Float.parseFloat(getTxtLange().getText()),
					getTxtFarben().getText().split(";"));
			model.setTeppich(teppich);
			zeigeInformationsfensterAn("Das Teppich wurde aufgenommen!");
		} catch (Exception exc) {
			zeigeFehlermeldungsfensterAn(exc.getMessage());
		}
	}

}
