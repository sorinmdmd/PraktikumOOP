package business;

public class Teppich {

	// kategorie des Buergeramtes
	private String kategorie;
	// Oeffnungszeiten
	private float artikelnummer;
	private float breite;
	// Strasse und Hausnummer des Buergeramtes
	private float strasseHNr;
	// farben des Buergeramtes
	private String[] farben;

	public Teppich(String kategorie, float artikelnummer, float breite, float strasseHNr,
			String[] farben) {
		this.kategorie = kategorie;
		this.artikelnummer = artikelnummer;
		this.breite = breite;
		this.strasseHNr = strasseHNr;
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

	public float getStrasseHNr() {
		return strasseHNr;
	}

	public void setStrasseHNr(float strasseHNr) {
		this.strasseHNr = strasseHNr;
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

	public String gibBuergeramtZurueck(char trenner) {
		return this.getKategorie() + trenner + this.getArtikelnummer() + trenner + this.getBreite() + trenner
				+ this.getStrasseHNr() + trenner + "\n" + this.getFarbenAlsString(trenner) + "\n";
	}
}