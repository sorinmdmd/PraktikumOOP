package business;

import java.util.Arrays;

public class Teppich {

	// kategorie des Buergeramtes
	private String kategorie;
	// Oeffnungszeiten
	private float artikelnummer;
	private float breite;
	// Strasse und Hausnummer des Buergeramtes
	private float lange;
	// farben des Buergeramtes
	private String[] farben;

	public Teppich(String kategorie, float artikelnummer, float breite, float lange, String[] farben) {
		this.kategorie = kategorie;
		this.artikelnummer = artikelnummer;
		this.breite = breite;
		this.lange = lange;
		this.farben = farben;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public float getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(float artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public float getBreite() {
		return breite;
	}

	public void setBreite(float breite) {
		this.breite = breite;
	}

	public float getLange() {
		return lange;
	}

	public void setlange(float lange) {
		this.lange = lange;
	}

	public String[] getFarben() {
		return farben;
	}

	public void setFarben(String[] farben) {
		this.farben = farben;
	}

	public String getFarbenAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for (i = 0; i < this.getFarben().length - 1; i++) {
			ergebnis = ergebnis + this.getFarben()[i] + trenner;
		}
		return ergebnis + this.getFarben()[i];
	}

	public String gibTeppichZurueck(char trenner) {
		return this.getKategorie() + trenner + this.getArtikelnummer() + trenner + this.getBreite() + trenner
				+ this.getLange() + trenner + this.getFarbenAlsString(trenner);
	}

	@Override
	public String toString() {
		return kategorie + " " + artikelnummer + " " + breite + " " + lange + " " + Arrays.toString(farben);
	}

}
