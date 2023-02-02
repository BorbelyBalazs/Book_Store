package com.progmatic.hibernate;

import java.util.Scanner;

public class View {

    Scanner sc = new Scanner(System.in);
    Controller cont = new Controller();

    public void run() {

        int choice;

        do {
            System.out.println("Mit szeretnél tenni?\n");
            System.out.println("1. Új könyv felvétele");
            System.out.println("2. Könyv adatainak módosítása");
            System.out.println("3. Könyv keresése");
            System.out.println("4. Könyv kivezetése a piacról");
            System.out.println("5. Új szerző felvétele");
            System.out.println("6. Szerző adatainak módosítása");
            System.out.println("7. Szerző törlése");
            System.out.println("8. Új szerződött könyvesbolt felvétele");
            System.out.println("9. Könyvesbolt szerződésének bontása");
            System.out.println("10. Hol van kevesebb mint 5db egy adott könyvből");
            System.out.println("11. Inicializálás");
            System.out.println("0. Kilépés");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> newBook();
                case 2 -> modifyBook();
                case 3 -> searchBook();
                case 4 -> oopBook();
                case 5 -> newAuthor();
                case 6 -> modifyAuthor();
                case 7 -> deleteAuthor();
                case 8 -> newStore();
                case 9 -> terminateContractWithStore();
                case 10 -> lowQuantity();
                case 11 -> cont.init();
                case 0 -> System.out.println("Viszlát");
                default -> System.out.println("Kérlek az alábbi opciók közül válassz!");

            }

        } while (choice != 0);

    }

    private void newBook() {
    }

    private void modifyBook() {
    }

    private void searchBook() {
    }

    private void oopBook() {
    }

    private void newAuthor() {
    }

    private void modifyAuthor() {
    }

    private void deleteAuthor() {
    }

    private void newStore() {
    }

    private void terminateContractWithStore() {
    }

    private void lowQuantity() {
    }
}
