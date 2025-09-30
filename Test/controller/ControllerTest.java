package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    // DAVID BASISDATA
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

    // MORTEN BASISDATA
    LocalDate startDato = LocalDate.of(2025, 9, 26);
    LocalDate slutDato = LocalDate.of(2025, 9, 26);
    Patient patientMorten = new Patient("222202-2031", "Morten Jakobsen", 75);
    Lægemiddel lægemiddelMorten = new Lægemiddel("Ibuprofen", 0.002, 0.003, 0.004, "Gram");


    // GERNER BASISDATA
    private static Lægemiddel lægemiddel;
    private static Patient patient;//anvendOrdinationPN

    LocalDate datoAnvendPN = LocalDate.parse("2025-09-26");
    Double antalAnvendPN = 30.0;

    @Test
    void opretDagligFastOrdinationTC1() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                1, 1, 1, 1);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddelMorten);
        assertEquals(patientMorten.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC2() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                0, 0, 0, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddelMorten);
        assertEquals(patientMorten.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC3() {
        slutDato = LocalDate.of(2025, 9, 27);
        lægemiddelMorten = null;
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                0, 1, 0, 1);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddelMorten);
        assertEquals(patientMorten.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC4() {
        lægemiddelMorten = null;
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                1, 0, 1, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddelMorten);
        assertEquals(patientMorten.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC5() {
        slutDato = LocalDate.of(2025, 9, 25);
        // DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
        //        0, 1, 0, 1);
        // Lægemiddel lMiddelTest = test.getLaegemiddel();
        // assertEquals(lMiddelTest, lægemiddelMorten);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                0, 1, 0, 1));
        assertEquals("Start dato skal være før slut dato", exception.getMessage());
    }
    @Test
    void opretDagligFastOrdinationTC6() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patientMorten, lægemiddelMorten,
                2, 2, 2, 2);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddelMorten);
        assertEquals(patientMorten.getOrdinationer().getLast(), test);
    }

    @Test
    void anbefaletDosisPrDøgnTC1() {
        patientMorten = new Patient("222202-2203", "Morten Jakobsen", 24);
        double result = Controller.anbefaletDosisPrDøgn(patientMorten, lægemiddelMorten);
        assertEquals(0.048, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC2() {
        patientMorten = new Patient("222202-2203", "Morten Jakobsen", 25);
        double result = Controller.anbefaletDosisPrDøgn(patientMorten, lægemiddelMorten);
        assertEquals(0.075, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC3() {
        patientMorten = new Patient("222202-2203", "Morten Jakobsen", 126);
        double result = Controller.anbefaletDosisPrDøgn(patientMorten, lægemiddelMorten);
        assertEquals(0.504, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC4() {
        patientMorten = new Patient("222202-2203", "Morten Jakobsen", 0);
        double result = Controller.anbefaletDosisPrDøgn(patientMorten, lægemiddelMorten);
        assertEquals(0, result);
    }


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
    void anvendOrdinationPNtC1() {
        PN pnTC1 = new PN(
                LocalDate.parse("2025-09-26"),
                LocalDate.parse("2025-09-27"), antalAnvendPN);
        Controller.anvendOrdinationPN(pnTC1,datoAnvendPN);
        assertEquals(pnTC1.getDatoerAnvendDosis().getLast(), datoAnvendPN);
    }

    @Test
    void anvendOrdinationPNtC2() {
        PN pnTC1 = new PN(
                LocalDate.parse("2025-09-17"),
                LocalDate.parse("2025-09-27"), antalAnvendPN);
        Controller.anvendOrdinationPN(pnTC1,datoAnvendPN);
        assertEquals(pnTC1.getDatoerAnvendDosis().getLast(), datoAnvendPN);
    }

    @Test
    void anvendOrdinationPNtC3() {
        PN pnTC3 = new PN(
                LocalDate.parse("2025-09-27"),
                LocalDate.parse("2025-09-28"), antalAnvendPN);
        Exception exception = assertThrows(RuntimeException.class, () ->
                Controller.anvendOrdinationPN(pnTC3,datoAnvendPN));

        assertEquals("dato er udenfor ordinations periode", exception.getMessage());
    }

    @Test
    void anvendOrdinationPNtC4() {
        PN pnTC3 = new PN(
                LocalDate.parse("2025-09-17"),
                LocalDate.parse("2025-09-20"), antalAnvendPN);
        Exception exception = assertThrows(RuntimeException.class, () ->
                Controller.anvendOrdinationPN(pnTC3,datoAnvendPN));

        assertEquals("dato er udenfor ordinations periode", exception.getMessage());
    }

    @BeforeAll
    static void beforeAll() {
        Controller.setStorage(new Storage());
        lægemiddel = Controller.opretLægemiddel("Elvanse", 20, 25, 30, "mg");
        patient =  Controller.opretPatient("010100-0101", "AHHHHHH!!!!",20);
    }

    @Test
    void opretPNOrdinationBasic() {
        //arrange sker før

        //act
        PN pn = Controller.opretPNOrdination(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 28), patient, lægemiddel, 30);

        //assert
        assertTrue(patient.getOrdinationer().contains(pn));
    }

    @Test
    void opretPNOrdinationGraenser() {
        //arrange sker før

        //act
        PN pn = Controller.opretPNOrdination(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 20), patient, null,1.0/1000);

        //assert
        assertTrue(patient.getOrdinationer().contains(pn));
    }

    @Test
    void opretPNOrdinationNullPatient() {
        //arrange sker før

        //act & assert
        assertThrows(NullPointerException.class, () -> Controller.opretPNOrdination(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 28), null, lægemiddel,30));

    }
    @Test
    void opretPNOrdinationSlutDatoFoerStartDato() {
        //arrange sker før

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretPNOrdination(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 9, 28), patient, lægemiddel,30));

        assertEquals("Startdato kan ikke være efter slutdato", exception.getMessage());

    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelGyldigeData() {
        //arrange
        Lægemiddel lægemiddelDerKiggesEfter = Controller.opretLægemiddel("Læger", 20, 25, 30,"mL");

        Patient
                patient1 = Controller.opretPatient("blabla", "0000", 20),
                patient2 = Controller.opretPatient("blabla", "0000", 60),
                patient3 = Controller.opretPatient("blabla", "0000", 80),
                patient4 = Controller.opretPatient("blabla", "0000", 20),
                patient5 = Controller.opretPatient("blabla", "0000", 60),
                patient6 = Controller.opretPatient("blabla", "0000", 80);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient1, lægemiddelDerKiggesEfter, 30);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient2, lægemiddelDerKiggesEfter, 30);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient3, lægemiddelDerKiggesEfter, 30);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient4, lægemiddel, 30);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient5, lægemiddel, 30);

        Controller.opretPNOrdination(
                LocalDate.of(2025, 10, 21),
                LocalDate.of(2025, 10, 28),
                patient6, lægemiddel, 30);

        //act
        int antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(0, 0, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(0, antalOrdinationer);

        //act
        antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(60, 60, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(1, antalOrdinationer);

        //act
        antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(0, 60, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(2, antalOrdinationer);

        //act
        antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(20, 80, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(3, antalOrdinationer);



        //test for ugyldigeDataudenExceptions
        //act
        antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(80, 20, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(0, antalOrdinationer);

        //act
        antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(-80, 0, lægemiddelDerKiggesEfter);

        //assert
        assertEquals(0, antalOrdinationer);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelNullException() {
        //act & assert
        assertThrows(NullPointerException.class, () -> Controller.antalOrdinationerPrVægtPrLægemiddel(20, 80, null));
    }

}