package mercatis.com;

public
class Produkt {
    private final int    fortlaufendenummer;
    private       String titel;
    private       String beschreibung;

    public
    Produkt ( int fortlaufendenummer , String titel , String beschreibung ) {
        this.fortlaufendenummer = fortlaufendenummer;
        this.titel              = titel;
        this.beschreibung       = beschreibung;
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
    void display ( ) {
        System.out.println ( "Nummer: " + fortlaufendenummer );
        System.out.println ( "Titel: " + titel );
        System.out.println ( "Beschreibung: " + beschreibung );
    }

}
