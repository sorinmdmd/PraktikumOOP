package gui;

import java.io.IOException;
import business.TeppichenModel;
import javafx.stage.Stage;

public class TeppichControl {
	
	private TeppichenModel model;
	private TeppichenView view;

	public TeppichControl(Stage stage) {
		super();
		
		this.model = new TeppichenModel();
		this.view = new TeppichenView(stage, model, this);
	}

	public void schreibeTeppichenInCsvDatei() {
		try {
			model.writeCSV();
			view.zeigeInformationsfensterAn("Die Teppichen wurden gespeichert!");
		} catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}
	
	public void leseAusDatei(String typ) {
		try {
			if ("csv".equals(typ)) {
				model.readCSV();
				view.zeigeInformationsfensterAn("Die Teppichen wurden gelesen!");
			} else {
				view.zeigeInformationsfensterAn("Noch nicht implementiert!");
			}
		} catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}
}
