package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ordination.*;
import storage.Storage;

@SuppressWarnings("UnusedReturnValue")
public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    /**
     * Opret og returner en PN ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Pre: antal > 0.
     */
    public static PN opretPNOrdination(
            LocalDate startDato, LocalDate slutDato, Patient patient, Lægemiddel lægemiddel,
            double antal) {

        if (slutDato.isBefore(startDato))
            throw new IllegalArgumentException("Startdato Skal være før slutdato");

        PN pNOrdination = new PN(startDato, slutDato, antal);

        if (lægemiddel != null)
            pNOrdination.setLægemiddel(lægemiddel);

        patient.addOrdinationer(pNOrdination);

        return pNOrdination;
    }

    /**
     * Opret og returner en DagligFast ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Pre: morgenAntal, middagAntal, aftenAntal, natAntal >= 0
     */
    public static DagligFast opretDagligFastOrdination(
            LocalDate startDato, LocalDate slutDato, Patient patient, Lægemiddel lægemiddel,
            double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {

            if (slutDato.isBefore(startDato)){
                throw new IllegalArgumentException("Start dato skal være før slut dato");
            }

            Dosis[] doser = {
                    new Dosis(LocalTime.parse("08:00"), morgenAntal),
                    new Dosis(LocalTime.parse("12:00"), middagAntal),
                    new Dosis(LocalTime.parse("18:00"), aftenAntal),
                    new Dosis(LocalTime.parse("22:00"), natAntal)
            };
            DagligFast dagligFast = new DagligFast(startDato, slutDato, doser);
            if (lægemiddel !=null){
                dagligFast.setLægemiddel(lægemiddel);
            }

            patient.addOrdinationer(dagligFast);

        return dagligFast;
    }

    /**
     * Opret og returner en DagligSkæv ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException,
     * og ordinationen oprettes ikke.
     * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige,
     * kastes en IllegalArgumentException, og ordinationen oprettes ikke.
     * Pre: I antalEnheder er alle tal >= 0.
     */
    public static DagligSkæv opretDagligSkævOrdination(
            LocalDate startDen, LocalDate slutDen, Patient patient, Lægemiddel lægemiddel,
            LocalTime[] klokkeSlet, double[] antalEnheder) {

        if (slutDen.isBefore(startDen))
            throw new IllegalArgumentException("Startdato Skal være før slutdato");

        if (klokkeSlet.length != antalEnheder.length)
            throw new IllegalArgumentException("Mængden af klokkeslet og Mængden af antal enheder stemmer ikke overens");


        //TODO
            //der mangler noget i dagligskæv som det er lige nu tror jeg -Gerner
        DagligSkæv dagligSkæv = new DagligSkæv(startDen, slutDen);

        return null;
    }

    /**
     * Tilføj en dato for anvendelse af PN ordinationen.
     * Hvis datoen ikke er indenfor ordinationens gyldighedsperiode,
     * kastes en IllegalArgumentException.
     */
    public static void anvendOrdinationPN(PN ordination, LocalDate dato) {
        if (dato.isBefore(ordination.getStartDato()) || dato.isAfter(ordination.getSlutDato()))
            throw new IllegalArgumentException("dato er udenfor ordinations periode");

        ordination.anvendDosis(dato);
    }

    /**
     * Returner den anbefalede dosis pr. døgn af lægemidlet til patienten
     * (afhænger af patientens vægt).
     */
    public static double anbefaletDosisPrDøgn(Patient patient, Lægemiddel lægemiddel) {
        double patientVægt = patient.getVægt();

        if (patientVægt < 25) {
            // let anvendes hvis patient vejer < 25 kg
            return lægemiddel.getEnhedPrKgPrDøgnLet();
        } else if (patientVægt <= 120) {
            // faktor der anvendes hvis 25 kg <= patient vægt <= 120 kg
            return lægemiddel.getEnhedPrKgPrDøgnNormal();
        } else {
            // faktor der anvendes hvis patient vægt > 120 kg
            return lægemiddel.getEnhedPrKgPrDøgnTung();
        }
    }

    /** Returner antal ordinationer for det givne vægtinterval og det givne lægemiddel. */
    public static int antalOrdinationerPrVægtPrLægemiddel(
            double vægtStart, double vægtSlut, Lægemiddel lægemiddel) {

        return 0;
    }

    public static List<Patient> getAllPatienter() {
        return storage.getAllPatienter();
    }

    public static List<Lægemiddel> getAllLægemidler() {
        return storage.getAllLægemidler();
    }

    public static Patient opretPatient(String cpr, String navn, double vægt) {
        Patient p = new Patient(cpr, navn, vægt);
        storage.storePatient(p);
        return p;
    }

    public static Lægemiddel opretLægemiddel(
            String navn, double enhedPrKgPrDøgnLet, double enhedPrKgPrDøgnNormal,
            double enhedPrKgPrDøgnTung, String enhed) {
        Lægemiddel lm = new Lægemiddel(navn, enhedPrKgPrDøgnLet,
                enhedPrKgPrDøgnNormal, enhedPrKgPrDøgnTung, enhed);
        storage.storeLægemiddel(lm);
        return lm;
    }

    public static void initStorage() {
        Patient jane = opretPatient("121256-0512", "Jane Jensen", 63.4);
        Patient finn = opretPatient("070985-1153", "Finn Madsen", 83.2);
        opretPatient("050972-1233", "Hans Jørgensen", 89.4);
        opretPatient("011064-1522", "Ulla Nielsen", 59.9);
        Patient ib = opretPatient("090149-2529", "Ib Hansen", 87.7);

        Lægemiddel acetylsalicylsyre = opretLægemiddel(
                "Acetylsalicylsyre", 0.1, 0.15,
                0.16, "Styk");
        Lægemiddel paracetamol = opretLægemiddel(
                "Paracetamol", 1, 1.5,
                2, "Ml");
        Lægemiddel fucidin = opretLægemiddel(
                "Fucidin", 0.025, 0.025,
                0.025, "Styk");
        opretLægemiddel(
                "Methotrexate", 0.01, 0.015,
                0.02, "Styk");

        opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-12"),
                jane, paracetamol, 123);

        opretPNOrdination(LocalDate.parse("2019-02-12"), LocalDate.parse("2019-02-14"),
                jane, acetylsalicylsyre, 3);

        opretPNOrdination(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-25"),
                ib, fucidin, 5);

        opretPNOrdination(LocalDate.parse("2019-01-01"), LocalDate.parse("2019.01-12"),
                jane, paracetamol, 123);

        opretDagligFastOrdination(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-12"),
                finn, fucidin, 2, 0, 1, 0);

        LocalTime[] kl = {
                LocalTime.parse("12:00"), LocalTime.parse("12:40"),
                LocalTime.parse("16:00"), LocalTime.parse("18:45")};
        double[] an = {0.5, 1, 2.5, 3};
        opretDagligSkævOrdination(LocalDate.parse("2019-01-23"), LocalDate.parse("2019-01-24"),
                finn, fucidin, kl, an);
    }
}