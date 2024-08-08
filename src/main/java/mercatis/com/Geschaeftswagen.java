package mercatis.com;

public
class Geschaeftswagen extends Produkt {
    private       int    kmStand;
    private       String parkplatz;

    public
    Geschaeftswagen (int fortlaufendenummer, String titel, String beschreibung, int kmStand , String parkplatz ) {
        super(fortlaufendenummer, titel, beschreibung );
        this.kmStand            = kmStand;
        this.parkplatz          = parkplatz;

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
    void display ( ) {
        System.out.println ( "Name: " + getTitel ( ) );
        System.out.println ( "Farbe: " + getBeschreibung ( ) );
        System.out.println ( "Kilometerstand: " + kmStand );
        System.out.println ( "Standort des Autos: " + parkplatz );
    }


}