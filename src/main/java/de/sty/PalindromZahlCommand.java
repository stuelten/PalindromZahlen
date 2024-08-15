/*
 * Copyright 2024 Timo Stülten (pionira GmbH)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.sty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PalindromZahlCommand {

    BigInteger anfangsZahl;
    BigInteger endZahl;

    PalindromZahlCommand(BigInteger anfangsZahl, BigInteger endZahl) {
        this.anfangsZahl = anfangsZahl;
        this.endZahl = endZahl;
    }

    public static void main(String[] args) {
        BigInteger anfangsZahl = null;
        BigInteger endZahl = null;
        PalindromZahlCommand kommando;

        if (args.length < 1 || args.length > 2) {
            hilfeAusgeben();
            System.exit(1);
        }

        try {
            anfangsZahl = new BigInteger(args[0]);

            if (args.length == 2) {
                endZahl = new BigInteger(args[1]);
            } else {
                endZahl = anfangsZahl;
            }

        } catch (NumberFormatException e) {
            hilfeAusgeben();
            System.exit(2);
        }

        kommando = new PalindromZahlCommand(anfangsZahl, endZahl);
        kommando.berechne();
    }

    static void hilfeAusgeben() {
        System.out.println("""
                PalindromZahlCommand
                
                Aufruf:
                PalindromZahlCommand <Startzahl> [<Endzahl>]
                
                Es werden die PalindromZahlen zu allen Zahlen zwischen <Startzahl> und <Endzahl> berechnet.
                <Endzahl> ist optional. Ist sie entfallen, dann erfolgt die Berechnung nur für <Startzahl>. 
                """);
    }

    void berechne() {
        long zahlenOhneLoesung = 0;
        List<BigInteger> zahlenOhneLoesungListe = new ArrayList<BigInteger>();
        long maximaleSchritte = 0;
        BigInteger zahlMitMaxSchritten = null;

        for (BigInteger i = anfangsZahl; i.compareTo(endZahl) <= 0; i = i.add(BigInteger.ONE)) {
            System.out.printf("Starte mit: %20d\n\n", i);
            long schritte = PalindromGrosseZahl.berechne(i);
            if (schritte < 0) {
                zahlenOhneLoesung++;
                zahlenOhneLoesungListe.add(i);
            }
            if (schritte > maximaleSchritte) {
                maximaleSchritte = schritte;
                zahlMitMaxSchritten = i;
            }
        }

        System.out.println();
        System.out.println("Ergebnis:");
        System.out.println("---------");
        System.out.println();
        System.out.println("Zahlen ohne Lösung:");
        for (BigInteger i : zahlenOhneLoesungListe) {
            System.out.printf("            %20s\n", i);
        }
        System.out.printf("Anzahl:     %20s\n", zahlenOhneLoesung);

        System.out.println();
        System.out.println("Zahl mit maximaler Anzahl an Schritten: ");
        System.out.printf("            %20s\n", zahlMitMaxSchritten);
        System.out.println("Maximale Anzahl an Schritten: ");
        System.out.printf("            %20s\n", maximaleSchritte);
    }

}
