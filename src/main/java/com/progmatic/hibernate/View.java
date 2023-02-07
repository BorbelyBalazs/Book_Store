package com.progmatic.hibernate;

import com.progmatic.hibernate.model.Store;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class View {

    Scanner sc = new Scanner(System.in);
    Controller cont = new Controller();

    public void run() {

        cont.mostBookDeliver();

        int choice;

        do {
            System.out.println("\nMit szeretnél tenni?\n");
            System.out.println("1. Új könyv felvétele");
            System.out.println("2. Könyv adatainak módosítása");
            System.out.println("3. Könyv keresése");
            System.out.println("4. Könyv kivezetése a piacról");
            System.out.println("5. Új szerző felvétele");
            System.out.println("6. Szerző adatainak módosítása");
            System.out.println("7. Szerző törlése");
            System.out.println("8. Új szerződött könyvesbolt felvétele");
            System.out.println("9. Könyvesbolt adatainak módosítása");
            System.out.println("10. Könyvesbolt szerződésének bontása");
            System.out.println("11. Hol van kevesebb mint 5db egy adott könyvből");
            System.out.println("12. Könyv szállítása bolthoz");
            System.out.println("13. Inicializálás (nem ajánlott :D ) ");
            System.out.println("0. Kilépés");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> newBook();
                case 2 -> modifyBook();
                case 3 -> searchBook();
                case 4 -> oopBook();
                case 5 -> newAuthor();
                case 6 -> modifyAuthor();
                case 7 -> deleteAuthor();
                case 8 -> newStore();
                case 9 -> modifyStore();
                case 10 -> terminateContractWithStore();
                case 11 -> cont.lowQuantity();
                case 12 -> carryBooks();
                case 13 -> cont.init();
                case 0 -> System.out.println("Viszlát");
                default -> System.out.println("Kérlek az alábbi opciók közül válassz!");

            }

        } while (choice != 0);

    }

    private void carryBooks() {
        System.out.println("Melyik boltba szeretnél szállítani? (ID alapján)");
        long sId = sc.nextLong();
        sc.nextLine();

        if(cont.checkStore(sId)) {
            System.out.println("\nIlyen azonosítójú bolt nincs az adatbázisban.");
        }

        Map<Long, Integer> bIdsAndQuant = new HashMap<>();
        long bId;
        int quant;
        System.out.println("""
                    Add meg mely könyveket szeretnéd szállítani! (ID szerint)
                    Utána add meg a mennyiséget!
                    Ha nem szeretnél több könyvet felvenni, nyomd meg a 0-át!""");
        do {
            System.out.println("Add meg a könyv ID-t");
            bId = sc.nextLong();

            if (bId == 0) break;

            System.out.println("Add meg a darabszámot!");
            quant = sc.nextInt();
            sc.nextLine();

            bIdsAndQuant.put(bId, quant);

        } while(true);

        cont.carryBooksToStores(bIdsAndQuant, sId);
    }

    private void modifyStore() {

        boolean b = true;

        do {
            System.out.println("Melyik azonosítójú boltot szeretnéd módosítani?");
            int id = sc.nextInt();
            sc.nextLine();

            if(cont.checkStore(id)) {
                System.out.println("\nIlyen azonosítójú bolt nincs az adatbázisban.");
                break;
            }

            System.out.println("Mit szeretnél módosítani?");
            System.out.println("1. Nevet");
            System.out.println("2. Címet");
            System.out.println("3. Vissza a főmenübe");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Add meg az új nevet!");
                    String newName = sc.nextLine();
                    cont.modifyStore(newName, id, true);
                    b = false;
                }
                case 2 -> {
                    System.out.println("Add meg az új címet!");
                    String newAddress = sc.nextLine();
                    cont.modifyStore(newAddress, id, false);
                    b = false;
                }
                case 3 -> b = false;
            }
        } while (b);
    }

    private void newBook() {

        System.out.println("Ki a könyv szerzője?");
        String authorOfBook = sc.nextLine();
        if(cont.isAuthorInTable(authorOfBook)) {

            System.out.println("Mi a könyv címe?");
            String title = sc.nextLine();
            System.out.println("Mi az isbn száma?");
            String isbn = sc.nextLine();
            System.out.println("Melyik évben jelent meg a könyv?");
            Year year = Year.of(sc.nextInt());
            sc.nextLine();

            cont.newBook(title, isbn, year, authorOfBook);
        } else {
            System.out.println("Ilyen szerző nincs az adatbázisban. Szeretnéd most felvenni a szerzőt? (i/n)");
            String choice = sc.nextLine();

            if (choice.equals("i")) {
                newAuthor();
            }
        }
    }

    public void modifyBook() {
        System.out.println("Hanyas azonosítójú könyvet szeretnéd módosítani?");
        long id = sc.nextLong();
        boolean b = true;

        do {
            System.out.println("Mit szeretnél módosítani?");
            System.out.println("1. Cím");
            System.out.println("2. ISBN");
            System.out.println("3. Megjelenés éve");
            System.out.println("4. Vissza a főmenübe");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Írd be az új címet!");
                    String newTitle = sc.nextLine();
                    cont.modifyBookTitle(id, newTitle);
                    b = false;
                }
                case 2 -> {
                    System.out.println("Írd be az új ISBN számot!");
                    String newISBN = sc.nextLine();
                    cont.modifyBookISBN(id, newISBN);
                    b = false;
                }
                case 3 -> {
                    System.out.println("Írd be az új évszámot!");
                    Year newYear = Year.of(sc.nextInt());
                    sc.nextLine();
                    cont.modifyBookYear(id, newYear);
                    b = false;
                }
                case 4 -> b = false;
                default -> System.out.println("Kérlek a megadott opciók közül válassz!");
            }

        } while(b);
    }

    public void searchBook() {

        boolean b = true;

        do {
            System.out.println("Mi alapján szeretnél keresni?");
            System.out.println("1. Cím (Minden könyv)");
            System.out.println("2. Cím (Csak elérhető könyvek)");
            System.out.println("3. ISBN");
            System.out.println("4. Szerző (Minden könyv)");
            System.out.println("5. Szerző (Csak elérhető könyvek)");
            System.out.println("6. Vissza a főmenübe");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Add meg a címet, vagy a cím egy részét!");
                    String searchedTitle = sc.nextLine();
                    cont.searchBookTitle(searchedTitle, true);
                    b = false;
                }
                case 2 -> {
                    System.out.println("Add meg a címet, vagy a cím egy részét!");
                    String searchedTitle = sc.nextLine();
                    cont.searchBookTitle(searchedTitle, false);
                    b = false;
                }
                case 3 -> {
                    System.out.println("Add meg az ISBN számot!");
                    String searchedIsbn = sc.nextLine();
                    cont.searchBookIsbn(searchedIsbn);
                    b = false;
                }
                case 4 -> {
                    System.out.println("Add meg a szerző nevét!");
                    String searchedAuthor = sc.nextLine();
                    cont.searchBookAuthor(searchedAuthor, true);
                    b = false;
                }
                case 5 -> {
                    System.out.println("Add meg a szerző nevét!");
                    String searchedAuthor = sc.nextLine();
                    cont.searchBookAuthor(searchedAuthor, false);
                    b = false;
                }
                case 6 -> b = false;
                default -> System.out.println("Kérlek a megadott opciók közül válassz!");
            }
        } while (b);
    }

    public void oopBook() {

        boolean b = true;

        do {
        System.out.println("Mi alapján szeretnéd kivezetni a piacról?");
        System.out.println("1. Cím");
        System.out.println("2. ISBN");
        System.out.println("3. Vissza a főmenübe");

        int choice = sc.nextInt();
        sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Add meg a könyv pontos címét");
                    String chosenTitle = sc.nextLine();
                    cont.oopBook(chosenTitle, true);
                    b = false;
                }
                case 2 -> {
                    System.out.println("Add meg a könyv ISBN számát");
                    String chosenIsbn = sc.nextLine();
                    cont.oopBook(chosenIsbn, false);
                    b = false;
                }
                case 3 -> b = false;
            }

        } while (b);
    }

    public void newAuthor() {
        System.out.println("Add meg a szerző nevét!");
        String name = sc.nextLine();
        System.out.println("Add meg a szerző születési dátumát! (év <ENTER>, hónap <ENTER>, nap <ENTER>)");
        LocalDate dob = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
        sc.nextLine();

        cont.newAuthor(name, dob);
    }

    public void modifyAuthor() {

        boolean b = true;

        do {
            System.out.println("Melyik azonosítójú szerzőt szeretnéd módosítani?");
            long id = sc.nextInt();
            sc.nextLine();

            System.out.println("Mit szeretnél módosítani?");
            System.out.println("1. Nevet");
            System.out.println("2. Születési dátumot");
            System.out.println("3. Vissza a főmenübe");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Add meg az új nevet!");
                    String newName = sc.nextLine();
                    cont.modifyAuthorName(newName, id);
                    b = false;
                }
                case 2 -> {
                    System.out.println("Add meg az új születési dátumot (év<ENTER>, hó<ENTER>, nap<ENTER");
                    LocalDate newDate = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    cont.modifyAuthorDate(newDate, id);
                    b = false;
                }
                case 3 -> b = false;
            }
        } while (b);
    }

    public void deleteAuthor() {

        System.out.println("Melyik azonosítójú szerzőt szeretnéd törölni");
        int id = sc.nextInt();
        sc.nextLine();
        cont.deleteAuthor(id);
    }

    public void newStore() {
        System.out.println("Add meg az új hely nevét!");
        String newStoreName = sc.nextLine();
        System.out.println("Add meg az új hely címét!");
        String newStoreAddress = sc.nextLine();

        Set<Long> ids = new HashSet<>();
        long choice;
        System.out.println("""
                    Mely könyvek legyenek a boltban? (ID szerint)
                    Minden könyvből 10 darab kerül a boltba.
                    Ha nem szeretnél több könyvet felvenni, nyomd meg a 0-át!""");
        do {
            choice = sc.nextLong();
            sc.nextLine();

            if (choice != 0) {
                ids.add(choice);
            }

        } while(choice != 0);

        cont.newStore(newStoreName, newStoreAddress, ids);
    }

    public void terminateContractWithStore() {

        System.out.println("Melyik azonosítójú bolttal szeretnél szerződést bontani?");
        long id = sc.nextLong();
        sc.nextLine();

        if(cont.checkStore(id)) {
            System.out.println("\nIlyen azonosítójú bolt nincs az adatbázisban.");
        } else {
            cont.terminateContractStore(id);
        }
    }

}
