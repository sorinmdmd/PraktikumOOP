package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import gui.TeppichenView;

public class TeppichenModel {
	private Teppich teppich;
	public TeppichenModel() {
			
	}

	public void writeCSV() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("TeppichenAusgabe.csv", true));
		aus.write(teppich.gibTeppichZurueck(';'));
		aus.newLine();
		aus.close();
	}

	public void readCSV() throws IOException {
		BufferedReader ein = new BufferedReader(new FileReader("Teppichen.csv"));
		String[] zeile = ein.readLine().split(";");
		this.teppich = new Teppich(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]),
				Float.parseFloat(zeile[3]), zeile[4].split("_"));
		ein.close();
	}
	
	public Teppich getTeppich() {
		return teppich;
	}

	public void setTeppich(Teppich teppich) {
		this.teppich = teppich;
	}
}
