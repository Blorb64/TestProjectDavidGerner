package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestMorten {

    LocalDate startDato = LocalDate.of(2025, 9, 26);
    LocalDate slutDato = LocalDate.of(2025, 9, 26);
    Patient patientMorten = new Patient("222202-2031", "Morten Jakobsen", 75);
    Lægemiddel lægemiddelMorten = new Lægemiddel("Ibuprofen", 0.002, 0.003, 0.004, "Gram");

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

}