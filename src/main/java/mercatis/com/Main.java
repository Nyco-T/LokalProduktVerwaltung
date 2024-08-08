package mercatis.com;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

public
class Main {
    private static final Scanner                       scanner              = new Scanner ( System.in );
    private static final ArrayList < Buch >            buecherListe         = new ArrayList <> ( );
    private static final ArrayList < Notebook >        notebookListe        = new ArrayList <> ( );
    private static final ArrayList < Geschaeftswagen > geschaeftswagenListe = new ArrayList <> ( );
    private static       int                           fortlaufendenummer   = 1;
    private static final SimpleDateFormat              dateFormat           = new SimpleDateFormat ( "dd.MM.yyyy" );


    public static
    void main ( String[] args ) {
        boolean running = true;
        while ( running ) {
            displayMainMenu ( );
            int choice = getMenuChoice ( );
            switch ( choice ) {
                case 1:
                    manageBuecher ( );
                    break;
                case 2:
                    manageNotebooks ( );
                    break;
                case 3:
                    manageGeschaeftswagen ( );
                    break;
                case 4:
                    running = false;
                    System.out.println ( "Programm wird beendet. Auf Wiedersehen!" );
                    break;
                default:
                    System.out.println ( "Ungültige Auswahl. Bitte versuchen Sie es erneut." );
            }
        }
        scanner.close ( );
    }


    private static
    void displayMainMenu ( ) {
        System.out.println ( "\n--- Produktverwaltung ---" );
        System.out.println ( "1. Bücher verwalten" );
        System.out.println ( "2. Notebooks verwalten" );
        System.out.println ( "3. Geschäftswagen verwalten" );
        System.out.println ( "4. Alle Prdukte anziegen" );
        System.out.println ( "5. Beenden" );
        System.out.print ( "Wählen Sie eine Option: " );
    }

    private static
    int getMenuChoice ( ) {
        while ( ! scanner.hasNextInt ( ) ) {
            System.out.println ( "Bitte geben Sie eine Zahl ein." );
            scanner.next ( );
        }
        return scanner.nextInt ( );
    }

    private static
    void manageBuecher ( ) {
        boolean running = true;
        while ( running ) {
            bookDisplayMenu ( );
            int choice = getBookMenuChoice ( );
            switch ( choice ) {
                case 1:
                    addBook ( );
                    break;
                case 2:
                    displayBooks ( );
                    break;
                case 3:
                    deleteBook ( );
                    break;
                case 4:
                    searchBooks ( );
                    break;
                case 5:
                    running = false;
                    System.out.println ( "Programm wird beendet. Auf Wiedersehen!" );
                    break;
                default:
                    System.out.println ( "Ungültige Auswahl. Bitte versuchen Sie es erneut." );
            }
        }
        scanner.close ( );
    }


    private static
    void bookDisplayMenu ( ) {
        System.out.println ( "\n--- Bücherverwaltung ---" );
        System.out.println ( "1. Buch hinzufügen" );
        System.out.println ( "2. Bücherliste anzeigen" );
        System.out.println ( "3. Buch löschen" );
        System.out.println ( "4. Buch suchen" );
        System.out.println ( "5. Beenden" );
        System.out.print ( "Wählen Sie eine Option: " );
    }

    private static
    int getBookMenuChoice ( ) {
        while ( ! scanner.hasNextInt ( ) ) {
            System.out.println ( "Bitte geben Sie eine Zahl ein." );
            scanner.next ( );
        }
        return scanner.nextInt ( );
    }

    private static
    void addBook ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie den Titel des Buches an: " );
        String titel = scanner.nextLine ( );
        System.out.print ( "Beschreiben Sie das Buch: " );
        String beschreibung = scanner.nextLine ( );
        System.out.print ( "Geben Sie den Vornamen des Autors an: " );
        String autorVorname = scanner.nextLine ( );
        System.out.print ( "Geben Sie den Nachnamen des Autors an: " );
        String autorNachname = scanner.nextLine ( );
        System.out.print ( "Geben Sie die Sprache in der das Buch verfasst wurde an: " );
        String buchSprache = scanner.nextLine ( );
        System.out.print ( "Geben Sie die ISBN an: " );
        String isbn = scanner.nextLine ( );

        Date erscheinungsdatum = null;
        while ( erscheinungsdatum == null ) {
            System.out.print ( "Wann ist das Buch erschienen? (Format: dd.MM.yyyy): " );
            String dateInput = scanner.nextLine ( );
            try {
                erscheinungsdatum = dateFormat.parse ( dateInput );
            }
            catch ( ParseException e ) {
                System.out.println ( "Ungültiges Datumsformat. Bitte versuchen Sie es erneut." );
            }
        }
        Buch buch = new Buch ( fortlaufendenummer , titel , beschreibung , autorVorname , autorNachname , buchSprache , isbn , erscheinungsdatum );
        buecherListe.add ( buch );
        fortlaufendenummer++;
        System.out.println ( "Buch wurde erfolgreich hinzugefügt." );
    }


    private static
    void displayBooks ( ) {
        if ( buecherListe.isEmpty ( ) ) {
            System.out.println ( "Die Bücherliste ist leer." );
        }
        else {
            System.out.println ( "\nGespeicherte Bücher:" );
            for (int i = 0 ; i < buecherListe.size ( ) ; i++) {
                System.out.println ( "Buch " + (i + 1) + ":" );
                buecherListe.get ( i ).display ( );
                System.out.println ( "---------------------" );
            }
        }
    }

    private static
    void deleteBook ( ) {
        if ( buecherListe.isEmpty ( ) ) {
            System.out.println ( "Die Bücherliste ist leer. Es gibt nichts zu löschen." );
            return;
        }

        displayBooks ( );
        System.out.print ( "Geben Sie die Nummer des zu löschenden Buches ein: " );
        int bookNumber = getMenuChoice ( );

        if ( bookNumber > 0 && bookNumber <= buecherListe.size ( ) ) {
            Buch removedBook = buecherListe.remove ( bookNumber - 1 );
            System.out.println ( "Das Buch '" + removedBook.getTitel ( ) + "' wurde erfolgreich gelöscht." );
        }
        else {
            System.out.println ( "Ungültige Buchnummer. Bitte versuchen Sie es erneut." );
        }
    }


    private static
    void searchBooks ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie einen Suchbegriff ein (Titel, Autor, ISBN): " );
        String searchTerm = scanner.nextLine ( ).toLowerCase ( );

        ArrayList < Buch > searchResults = new ArrayList <> ( );
        for (Buch buch : buecherListe) {
            if ( buch.getTitel ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    buch.getAutorVorname ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    buch.getAutorNachname ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    buch.getIsbn ( ).contains ( searchTerm ) ) {
                searchResults.add ( buch );
            }
        }


        if ( searchResults.isEmpty ( ) ) {
            System.out.println ( "Keine Bücher gefunden." );
        }
        else {
            System.out.println ( "\nGefundene Bücher:" );
            for (int i = 0 ; i < searchResults.size ( ) ; i++) {
                System.out.println ( "Buch " + (i + 1) + ":" );
                searchResults.get ( i ).display ( );
                System.out.println ( "---------------------" );
            }
        }
    }


    private static
    void manageNotebooks ( ) {
        boolean running = true;
        while ( running ) {
            notebookDisplayMenu ( );
            int choice = getNotebookMenuChoice ( );
            switch ( choice ) {
                case 1:
                    addNotebook ( );
                    break;
                case 2:
                    displayNotebooks ( );
                    break;
                case 3:
                    deleteNotebook ( );
                    break;
                case 4:
                    searchNotebooks ( );
                    break;
                case 5:
                    running = false;
                    System.out.println ( "Programm wird beendet. Auf Wiedersehen!" );
                    break;
                default:
                    System.out.println ( "Ungültige Auswahl. Bitte versuchen Sie es erneut." );
            }
        }
        scanner.close ( );
    }

    private static
    void notebookDisplayMenu ( ) {
        System.out.println ( "\n--- Notebookverwaltung ---" );
        System.out.println ( "1. Notebook hinzufügen" );
        System.out.println ( "2. Notebook anzeigen" );
        System.out.println ( "3. Notebook löschen" );
        System.out.println ( "4. Notebook suchen" );
        System.out.println ( "5. Beenden" );
        System.out.print ( "Wählen Sie eine Option: " );
    }

    private static
    int getNotebookMenuChoice ( ) {
        while ( ! scanner.hasNextInt ( ) ) {
            System.out.println ( "Bitte geben Sie eine Zahl ein." );
            scanner.next ( );
        }
        return scanner.nextInt ( );
    }

    private static
    void addNotebook ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie den Titel des Notebooks an: " );
        String titel = scanner.nextLine ( );
        System.out.print ( "Beschreiben Sie das Notebooks: " );
        String beschreibung = scanner.nextLine ( );
        System.out.print ( "Wie viel Speicherplatz hat ihr Notebook?: " );
        int speicherplatz = scanner.nextInt ( );
        scanner.nextLine ( );
        System.out.print ( "Welcher Prozessor is in Ihrem Notebook verbaut?: " );
        String   prozessor = scanner.nextLine ( );
        Notebook notebook  = new Notebook ( fortlaufendenummer , titel , beschreibung , speicherplatz , prozessor );
        notebookListe.add ( notebook );
        fortlaufendenummer++;
        System.out.println ( "Buch wurde erfolgreich hinzugefügt." );
    }

    private static
    void displayNotebooks ( ) {
        if ( notebookListe.isEmpty ( ) ) {
            System.out.println ( "Die Notebookliste ist leer." );
        }
        else {
            System.out.println ( "\nGespeicherte Notebooks:" );
            for (int i = 0 ; i < notebookListe.size ( ) ; i++) {
                System.out.println ( "Notebook " + (i + 1) + ":" );
                notebookListe.get ( i ).darstellen ( );
                System.out.println ( "---------------------" );
            }
        }
    }


    private static
    void deleteNotebook ( ) {
        if ( notebookListe.isEmpty ( ) ) {
            System.out.println ( "Die Notebookliste ist leer. Es gibt nichts zu löschen." );
            return;
        }

        displayNotebooks ( );
        System.out.print ( "Geben Sie die Nummer des zu löschenden Notebooks ein: " );
        int NotebookNumber = getMenuChoice ( );

        if ( NotebookNumber > 0 && NotebookNumber <= notebookListe.size ( ) ) {
            Notebook removedNotebook = notebookListe.remove ( NotebookNumber - 1 );
            System.out.println ( "Das Notebook '" + removedNotebook.getTitel ( ) + "' wurde erfolgreich gelöscht." );
        }
        else {
            System.out.println ( "Ungültige Notebooknummer. Bitte versuchen Sie es erneut." );
        }
    }


    private static
    void searchNotebooks ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie einen Suchbegriff ein (Titel, Autor, Prozessor): " );
        String searchTerm = scanner.nextLine ( ).toLowerCase ( );

        ArrayList < Notebook > searchResults = new ArrayList <> ( );
        for (Notebook notebook : notebookListe) {
            if ( notebook.getTitel ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    notebook.getBeschreibung ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    notebook.getProzessor ( ).toLowerCase ( ).contains ( searchTerm ) ) {
                searchResults.add ( notebook );
            }
        }


        if ( searchResults.isEmpty ( ) ) {
            System.out.println ( "Keine Bücher gefunden." );
        }
        else {
            System.out.println ( "\nGefundene Bücher:" );
            for (int i = 0 ; i < searchResults.size ( ) ; i++) {
                System.out.println ( "Buch " + (i + 1) + ":" );
                searchResults.get ( i ).darstellen ( );
                System.out.println ( "---------------------" );
            }
        }
    }

    private static
    void manageGeschaeftswagen ( ) {
        boolean running = true;
        while ( running ) {
            wagenDisplayMenu ( );
            int choice = getWagenMenuChoice ( );
            switch ( choice ) {
                case 1:
                    addWagen ( );
                    break;
                case 2:
                    displayWagen ( );
                    break;
                case 3:
                    deleteWagen ( );
                    break;
                case 4:
                    searchWagen ( );
                    break;
                case 5:
                    running = false;
                    System.out.println ( "Programm wird beendet. Auf Wiedersehen!" );
                    break;
                default:
                    System.out.println ( "Ungültige Auswahl. Bitte versuchen Sie es erneut." );
            }
        }
        scanner.close ( );
    }

    private static
    void wagenDisplayMenu ( ) {
        System.out.println ( "\n--- Geschäftswagenverwaltung ---" );
        System.out.println ( "1. Auto hinzufügen" );
        System.out.println ( "2. Auto anzeigen" );
        System.out.println ( "3. Auto löschen" );
        System.out.println ( "4. Auto suchen" );
        System.out.println ( "5. Beenden" );
        System.out.print ( "Wählen Sie eine Option: " );
    }

    private static
    int getWagenMenuChoice ( ) {
        while ( ! scanner.hasNextInt ( ) ) {
            System.out.println ( "Bitte geben Sie eine Zahl ein." );
            scanner.next ( );
        }
        return scanner.nextInt ( );
    }

    private static
    void addWagen ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie den Namen des Autos an: " );
        String titel = scanner.nextLine ( );
        System.out.print ( "Welche Farbe hat Ihr Auto das Auto: " );
        String beschreibung = scanner.nextLine ( );
        System.out.print ( "Wie viel Kilometer ist ihr Auto gefahren?: " );
        int kmStand = scanner.nextInt ( );
        scanner.nextLine ( );
        System.out.print ( "Wo steht Ihr Auto?: " );
        String          parkplatz = scanner.nextLine ( );
        Geschaeftswagen auto      = new Geschaeftswagen ( fortlaufendenummer , titel , beschreibung , kmStand , parkplatz );
        geschaeftswagenListe.add ( auto );
        Geschaeftswagen geschaeftswagen = new Geschaeftswagen ( fortlaufendenummer , titel , beschreibung , kmStand , parkplatz );
        geschaeftswagenListe.add ( geschaeftswagen );
        fortlaufendenummer++;
        System.out.println ( "Buch wurde erfolgreich hinzugefügt." );
    }


    private static
    void displayWagen ( ) {
        if ( geschaeftswagenListe.isEmpty ( ) ) {
            System.out.println ( "Die Autoliste ist leer." );
        }
        else {
            System.out.println ( "\nGespeicherte Autos:" );
            for (int i = 0 ; i < geschaeftswagenListe.size ( ) ; i++) {
                System.out.println ( "Auto " + (i + 1) + ":" );
                geschaeftswagenListe.get ( i ).anzeigen ( );
                System.out.println ( "---------------------" );
            }
        }
    }

    private static
    void deleteWagen ( ) {
        if ( geschaeftswagenListe.isEmpty ( ) ) {
            System.out.println ( "Die Autoliste ist leer. Es gibt nichts zu löschen." );
            return;
        }
        displayWagen ( );
        System.out.print ( "Geben Sie die Nummer des zu löschenden Autos ein: " );
        int wagenNummer = getMenuChoice ( );

        if ( wagenNummer > 0 && wagenNummer <= geschaeftswagenListe.size ( ) ) {
            Geschaeftswagen removedWagen = geschaeftswagenListe.remove ( wagenNummer - 1 );
            System.out.println ( "Das Auto '" + removedWagen.getTitel ( ) + "' wurde erfolgreich gelöscht." );
        }
        else {
            System.out.println ( "Ungültige Autonummer. Bitte versuchen Sie es erneut." );
        }
    }

    private static
    void searchWagen ( ) {
        scanner.nextLine ( );
        System.out.print ( "Geben Sie einen Suchbegriff ein (Name, Beschreibung, Standort): " );
        String searchTerm = scanner.nextLine ( ).toLowerCase ( );

        ArrayList < Geschaeftswagen > searchResults = new ArrayList <> ( );
        for (Geschaeftswagen geschaeftswagen : geschaeftswagenListe) {
            if ( geschaeftswagen.getTitel ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    geschaeftswagen.getBeschreibung ( ).toLowerCase ( ).contains ( searchTerm ) ||
                    geschaeftswagen.getParkplatz ( ).toLowerCase ( ).contains ( searchTerm ) ) {
                searchResults.add ( geschaeftswagen );
            }
        }
        if ( searchResults.isEmpty ( ) ) {
            System.out.println ( "Keine Autos gefunden." );
        }
        else {
            System.out.println ( "\nGefundene Autos:" );
            for (int i = 0 ; i < searchResults.size ( ) ; i++) {
                System.out.println ( "Buch " + (i + 1) + ":" );
                searchResults.get ( i ).anzeigen ( );
                System.out.println ( "---------------------" );
            }
        }
    }


}