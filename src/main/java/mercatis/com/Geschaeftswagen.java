package mercatis.com;

public
class Geschaeftswagen {
    private final int    fortlaufendenummer;
    private       String titel;
    private       String beschreibung;
    private       int    kmStand;
    private       String parkplatz;

    public
    Geschaeftswagen ( int fortlaufendenummer , String titel , String beschreibung , int kmStand , String parkplatz ) {
        this.fortlaufendenummer = fortlaufendenummer;
        this.titel              = titel;
        this.beschreibung       = beschreibung;
        this.kmStand            = kmStand;
        this.parkplatz          = parkplatz;

    }

    public
    String getTitel ( ) {
        return titel;
    }

    public
    void setTitel ( String titel ) {
        this.titel = titel;
    }

    public
    String getBeschreibung ( ) {
        return beschreibung;
    }

    public
    void setBeschreibung ( String beschreibung ) {
        this.beschreibung = beschreibung;
    }

    public
    int getKmStand ( ) { return kmStand; }

    public
    void setKmStand ( int kmStand ) {
        this.kmStand = kmStand;
    }

    public
    String getParkplatz ( ) {
        return parkplatz;
    }

    public
    void setParkplatz ( String parkplatz ) {
        this.parkplatz = parkplatz;
    }


    public
    void anzeigen ( ) {
        System.out.println ( "Nummer: " + fortlaufendenummer );
        System.out.println ( "Name des Autos:" + titel );
        System.out.println ( "Beschreibung: " + beschreibung );
        System.out.println ( "Kilometerstand: " + kmStand );
        System.out.println ( "Standort des Autos: " + parkplatz );
    }


}