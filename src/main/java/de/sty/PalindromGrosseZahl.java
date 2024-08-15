package de.sty;

import java.math.BigInteger;

public class PalindromGrosseZahl {

    public static BigInteger umkehrZahl(BigInteger quellZahl) {
        BigInteger gedreht = BigInteger.ZERO;
        BigInteger zehn = BigInteger.TEN;

        while (quellZahl.compareTo(BigInteger.ZERO) > 0) {
            BigInteger ziffer = quellZahl.mod(zehn); // Letzte Ziffer bestimmen
            gedreht = gedreht.multiply(zehn).add(ziffer); // Alle Ziffern um eins nach links schubsen und neue ziffer rechts anfügen
            quellZahl = quellZahl.divide(zehn); // letzte Ziffer entfernen
        }

        return gedreht;
    }

    public static boolean istKeinePalindromZahl(BigInteger number) {
        // Zahl als Text verarbeiten
        String str = number.toString();

        // Textlänge
        int len = str.length();

        // Vergleiche Zeichen von vorne und hinten zur Mitte hin
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                // Zwei Zeichen sind nicht gleich, also ist es keine Palindromzahl
                return true;
            }
        }

        // Alle Zeichen sind gleich, also ist es eine Palindromzahl
        return false;
    }

    public static int berechne(BigInteger startWert) {
        int schritt = 0;
        BigInteger aktuellerWert = startWert;

        while (istKeinePalindromZahl(aktuellerWert) && schritt < 32) {
            schritt = schritt + 1;
            System.out.printf("  Schritt %02d\n", schritt);
            System.out.printf("            %20s\n", aktuellerWert);
            BigInteger umkehrZahl = umkehrZahl(aktuellerWert);
            System.out.printf("           +%20s\n", umkehrZahl);
            aktuellerWert = aktuellerWert.add(umkehrZahl);
            System.out.println("           ---------------------");
            System.out.printf("            %20s\n", aktuellerWert);
            System.out.println();
        }

        if (istKeinePalindromZahl(aktuellerWert)) {
            System.out.printf("Kein Ergebnis nach %d Schritten!\n", schritt);
            schritt = -1;
        }

        System.out.printf("Ergebnis:   %20d\n", aktuellerWert);
        System.out.printf("Schritte:   %20d\n", schritt);
        System.out.println();

        return schritt;
    }

}
