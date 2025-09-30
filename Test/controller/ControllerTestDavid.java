package controller;

import ordination.DagligSkæv;
import ordination.Lægemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestDavid {
    private Patient patientDS = new Patient("123456-0987", "David Davidsen", 96);
    private Lægemiddel lægemiddelDS = new Lægemiddel("Acetylsalicylsyre", 1,2,3, "Styk");
    private LocalTime[] klokkesletDS = {
            LocalTime.parse("08:00"),
            LocalTime.parse("12:00"),
            LocalTime.parse("16:00"),
            LocalTime.parse("20:00")
    };
    private double[] antalEnhederDS = {2,2,2,2};
    private LocalDate starDenDS = LocalDate.parse("2025-11-11");
    private LocalDate slutDenDS = LocalDate.parse("2025-11-18");

    //anvendOrdinationPN

    LocalDate datoAnvendPN = LocalDate.parse("2025-09-26");
    Double antalAnvendPN = 30.0;

    @Test
    void opretDagligSkævOrdinationTC1() {
        LocalDate slutDenTC = LocalDate.parse("2025-11-11");
        DagligSkæv TC1 = Controller.opretDagligSkævOrdination(
                this.starDenDS,
                slutDenTC,
                patientDS, lægemiddelDS, klokkesletDS, antalEnhederDS);
        assertEquals(patientDS.getOrdinationer().getLast(), TC1);
    }

    @Test
    void opretDagligSkævOrdinationTC2() {
        LocalDate slutDenTC = LocalDate.parse("2025-12-11");
        DagligSkæv TC2 = Controller.opretDagligSkævOrdination(
                this.starDenDS,
                slutDenTC,
                patientDS, lægemiddelDS, klokkesletDS, antalEnhederDS);
        assertEquals(patientDS.getOrdinationer().getLast(), TC2);
    }

    @Test
    void opretDagligSkævOrdinationTC3() {
        DagligSkæv TC3 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                patientDS, lægemiddelDS, klokkesletDS, antalEnhederDS);
        assertEquals(patientDS.getOrdinationer().getLast(), TC3);
    }

    @Test
    void opretDagligSkævOrdinationTC4() {
        LocalTime[] klokkesletTC = new LocalTime[0];
        double[] antalEnhederTC = {};

        DagligSkæv TC4 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                patientDS, lægemiddelDS, klokkesletTC, antalEnhederTC);
        assertEquals(patientDS.getOrdinationer().getLast(), TC4);
    }

    @Test
    void opretDagligSkævOrdinationTC5() {
        double[] antalEnhederTC = {0,0,0,0};

        DagligSkæv TC5 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                patientDS, lægemiddelDS, klokkesletDS, antalEnhederTC);
        assertEquals(patientDS.getOrdinationer().getLast(), TC5);
    }

    @Test
    void opretDagligSkævOrdinationTC6() {
        Patient patientTC = new Patient("654321-0987", "Divad Nesdivad", 80);

        DagligSkæv TC6 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                patientTC, lægemiddelDS, klokkesletDS, antalEnhederDS);
        assertEquals(patientTC.getOrdinationer().getLast(), TC6);
    }

    @Test
    void opretDagligSkævOrdinationTC7() {

        DagligSkæv TC7 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                patientDS, null, klokkesletDS, antalEnhederDS);
        assertEquals(patientDS.getOrdinationer().getLast(), TC7);
    }

    @Test
    void opretDagligSkævOrdinationTC8() {


        Exception exception = assertThrows(RuntimeException.class, () ->
                Controller.opretDagligSkævOrdination(
                LocalDate.parse("2025-11-14"),
                LocalDate.parse("2025-11-11"),
                patientDS, lægemiddelDS, klokkesletDS, antalEnhederDS));

        assertEquals("Startdato Skal være før slutdato", exception.getMessage());
    }

    @Test
    void opretDagligSkævOrdinationTC9() {

        LocalTime[] klokkesletTC = {
                LocalTime.parse("08:00"),
                LocalTime.parse("14:00"),
                LocalTime.parse("22:00"),
        };

        Exception exception = assertThrows(RuntimeException.class, () ->
                Controller.opretDagligSkævOrdination(
                        starDenDS,
                        slutDenDS,
                        patientDS, lægemiddelDS, klokkesletTC, antalEnhederDS));

        assertEquals("Mængden af klokkeslet og Mængden af antal enheder stemmer ikke overens", exception.getMessage());
    }

    @Test
    void opretDagligSkævOrdinationTC10() {

        DagligSkæv TC7 = Controller.opretDagligSkævOrdination(
                starDenDS,
                starDenDS,
                null, lægemiddelDS, klokkesletDS, antalEnhederDS);
        assertNull(TC7);
    }



    /*
    @Test
    void anvendOrdinationPN() {
        PN pnTC1 = new PN(
                LocalDate.parse("2025-09-26"),
                LocalDate.parse("2025-09-27"), antalAnvendPN);
        Controller.anvendOrdinationPN(pnTC1,datoAnvendPN);
        assertEquals(pnTC1.().getLast(), TC6);
    }

     */
}