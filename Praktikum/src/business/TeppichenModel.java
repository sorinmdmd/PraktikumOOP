package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeppichenModel {
	private Teppich teppich;
	List<Teppich> teppichList = new ArrayList<>();

	public TeppichenModel() {

	}

	public void writeCSV() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("TeppichenAusgabe.csv", true));
		aus.write(teppich.gibTeppichZurueck(';'));
		aus.newLine();
		aus.close();
	}

	public void readCSV() throws IOException {
		BufferedReader ein = new BufferedReader(new FileReader("TeppichenAusgabe.csv"));

		String line;
		while ((line = ein.readLine()) != null) {
			String[] zeile = line.split(";");
			Teppich teppich = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]),
					Float.parseFloat(zeile[3]), zeile[4].split("_"));
			teppichList.add(teppich);
		}

		ein.close();
	}

	public String getTeppichen() {
		String gesamtListe = "";

		for (int i = 0; i < teppichList.size(); i++) {
			gesamtListe += teppichList.get(i).toString() + "\n";
		}
		return gesamtListe;
	}

	public Teppich getTeppich() {
		return teppich;
	}

	public void setTeppich(Teppich teppich) {
		this.teppich = teppich;
	}
}
