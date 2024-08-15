package de.sty;

import java.util.ArrayList;
import java.util.List;

public class PalindromZahl {

    public static long umkehrZahl(long quellZahl) {
        long gedreht = 0;

        while (quellZahl > 0) {
            long ziffer = quellZahl % 10; // Letzte Ziffer bestimmen
            gedreht = gedreht * 10 + ziffer; // Alle Ziffern um eins nach links schubsen und neue ziffer rechts anfügen
            quellZahl /= 10; // letzte Ziffer entfernen
        }

        return gedreht;
    }

    public static boolean istKeinePalindromZahl(long number) {
        // Convert the number to a string
        String str = Long.toString(number);

        // Get the length of the string
        int len = str.length();

        // Compare characters from the start and end moving towards the center
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return true; // If any character doesn't match, it's not a palindrome
            }
        }

        return false; // All characters matched, it is a palindrome
    }

    public static int berechne(long startWert) {
        int schritt = 0;
        long aktuellerWert = startWert;

        while (istKeinePalindromZahl(aktuellerWert) && schritt < 40) {
            schritt = schritt + 1;
            System.out.printf("Schritt %02d\n", schritt);
            System.out.printf("            %20d\n", aktuellerWert);
            long umkehrZahl = umkehrZahl(aktuellerWert);
            System.out.printf("           +%20d\n", umkehrZahl);
            aktuellerWert = aktuellerWert + umkehrZahl;
            System.out.println("          ---------------------");
            System.out.printf("            %20d\n", aktuellerWert);
            System.out.println();
        }

        if (istKeinePalindromZahl(aktuellerWert)) {
            System.out.printf("Kein Ergebnis nach %d Schritten!\n", schritt);
        } else {
            System.out.printf("Ergebnis:   %20d\n", aktuellerWert);
        }

        return schritt;
    }

    public static void main(String[] args) {
        List<Integer> ergebnisse = new ArrayList<>();
        for (int i = 10000; i < 100000; i++) {
            Integer schritte = berechne(i);
            ergebnisse.add(schritte);
        }

        int pos = 10000;
        for (Integer i : ergebnisse) {
            System.err.printf("%02d;%03d;\n", pos, i);
            pos++;
        }

    }

}
