package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Teppich;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class TeppichenAnwendersystem {

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblName = new Label("Kategorie:");
	private Label lblGeoeffnetVon = new Label("Artikelnummer");
	private Label lblGeoeffnetBis = new Label("Breite");
	private Label lblStrasseHNr = new Label("lange");
	private Label lblDienstleistungen = new Label("Farben:");
	private TextField txtName = new TextField();
	private TextField txtGeoeffnetVon = new TextField();
	private TextField txtGeoeffnetBis = new TextField();
	private TextField txtStrasseHNr = new TextField();
	private TextField txtDienstleistungen = new TextField();
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
	private Teppich teppich;

	public TeppichenAnwendersystem(Stage primaryStage) {
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
		lblName.setLayoutX(20);
		lblName.setLayoutY(90);
		lblGeoeffnetVon.setLayoutX(20);
		lblGeoeffnetVon.setLayoutY(130);
		lblGeoeffnetBis.setLayoutX(20);
		lblGeoeffnetBis.setLayoutY(170);
		lblStrasseHNr.setLayoutX(20);
		lblStrasseHNr.setLayoutY(210);
		lblDienstleistungen.setLayoutX(20);
		lblDienstleistungen.setLayoutY(250);
		pane.getChildren().addAll(lblEingabe, lblAnzeige, lblName, lblGeoeffnetVon, lblGeoeffnetBis, lblStrasseHNr,
				lblDienstleistungen);

		// Textfelder
		txtName.setLayoutX(170);
		txtName.setLayoutY(90);
		txtName.setPrefWidth(200);
		txtGeoeffnetVon.setLayoutX(170);
		txtGeoeffnetVon.setLayoutY(130);
		txtGeoeffnetVon.setPrefWidth(200);
		txtGeoeffnetBis.setLayoutX(170);
		txtGeoeffnetBis.setLayoutY(170);
		txtGeoeffnetBis.setPrefWidth(200);
		txtStrasseHNr.setLayoutX(170);
		txtStrasseHNr.setLayoutY(210);
		txtStrasseHNr.setPrefWidth(200);
		txtDienstleistungen.setLayoutX(170);
		txtDienstleistungen.setLayoutY(250);
		txtDienstleistungen.setPrefWidth(200);
		pane.getChildren().addAll(txtName, txtGeoeffnetVon, txtGeoeffnetBis, txtStrasseHNr, txtDienstleistungen);

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
				leseAusDatei("csv");
			}
		});
		mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				leseAusDatei("txt");
			}
		});
		mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeTeppichenInCsvDatei();
			}
		});
	}

	private void nehmeTeppichAuf() {
		try {
			this.teppich = new Teppich(txtName.getText(), Float.parseFloat(txtGeoeffnetVon.getText()),
					Float.parseFloat(txtGeoeffnetBis.getText()), Float.parseFloat(txtStrasseHNr.getText()),
					txtDienstleistungen.getText().split(";"));
			zeigeInformationsfensterAn("Das Bürgeramt wurde aufgenommen!");
		} catch (Exception exc) {
			zeigeFehlermeldungsfensterAn(exc.getMessage());
		}
	}

	private void zeigeTeppichAn() {
		if (this.teppich != null) {
			txtAnzeige.setText(this.teppich.gibTeppichZurueck(' '));
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Bürgeramt aufgenommen!");
		}
	}

	private void leseAusDatei(String typ) {
		try {
			if ("csv".equals(typ)) {
				BufferedReader ein = new BufferedReader(new FileReader("Teppichen.csv"));
				String[] zeile = ein.readLine().split(";");
				this.teppich = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]),
						Float.parseFloat(zeile[3]), zeile[4].split("_"));
				ein.close();
				zeigeInformationsfensterAn("Die Teppichen wurden gelesen!");
			} else {
				zeigeInformationsfensterAn("Noch nicht implementiert!");
			}
		} catch (IOException exc) {
			zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}

	private void schreibeTeppichenInCsvDatei() {
		try {
			BufferedWriter aus = new BufferedWriter(new FileWriter("TeppichenAusgabe.csv", true));
			aus.write(teppich.gibTeppichZurueck(';'));
			aus.close();
			zeigeInformationsfensterAn("Die Teppichen wurden gespeichert!");
		} catch (IOException exc) {
			zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}

	private void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

	void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

}
