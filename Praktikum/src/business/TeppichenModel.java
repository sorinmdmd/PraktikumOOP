package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import gui.TeppichenView;

public class TeppichenModel {
	private Teppich teppich;
	
	private TeppichenView view;
	
	public TeppichenModel() {
		
	}
	
	public void schreibeTeppichenInCsvDatei() {
		try {
			BufferedWriter aus = new BufferedWriter(new FileWriter("TeppichenAusgabe.csv", true));
			aus.write(teppich.gibTeppichZurueck(';'));
			aus.close();
			view.zeigeInformationsfensterAn("Die Teppichen wurden gespeichert!");
		} catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}
	
	
	public void nehmeTeppichAuf() {
		try {
			this.teppich = new Teppich(view.getTxtKategorie().getText(), Float.parseFloat(view.getTxtArtikelnummer().getText()),
					Float.parseFloat(view.getTxtBreite().getText()), Float.parseFloat(view.getTxtLange().getText()),
					view.getTxtFarben().getText().split(";"));
			view.zeigeInformationsfensterAn("Das Teppich wurde aufgenommen!");
		} catch (Exception exc) {
			view.zeigeFehlermeldungsfensterAn(exc.getMessage());
		}
	}
}
