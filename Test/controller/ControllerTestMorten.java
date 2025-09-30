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
    Patient patient = new Patient("222202-2031", "Morten Jakobsen", 75);
    Lægemiddel lægemiddel = new Lægemiddel("Ibuprofen", 0.002, 0.003, 0.004, "Gram");

    @Test
    void opretDagligFastOrdinationTC1() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                1, 1, 1, 1);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(patient.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC2() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                0, 0, 0, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(patient.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC3() {
        slutDato = LocalDate.of(2025, 9, 27);
        lægemiddel = null;
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                0, 1, 0, 1);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(patient.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC4() {
        lægemiddel = null;
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                1, 0, 1, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(patient.getOrdinationer().getLast(), test);
    }
    @Test
    void opretDagligFastOrdinationTC5() {
        slutDato = LocalDate.of(2025, 9, 25);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                0, 1, 0, 1);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(null, test);
    }
    @Test
    void opretDagligFastOrdinationTC6() {
        slutDato = LocalDate.of(2025, 9, 27);
        patient = null;
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                0, 0, 0, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(null, test);
    }
    @Test
    void opretDagligFastOrdinationTC7() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                -1, 0, 0, 0);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(null, test);
    }
    @Test
    void opretDagligFastOrdinationTC8() {
        slutDato = LocalDate.of(2025, 9, 27);
        DagligFast test = Controller.opretDagligFastOrdination(startDato, slutDato, patient, lægemiddel,
                2, 2, 2, 2);
        Lægemiddel lMiddelTest = test.getLaegemiddel();
        assertEquals(lMiddelTest, lægemiddel);
        assertEquals(null, test);
    }

    @Test
    void anbefaletDosisPrDøgnTC1() {
        patient = new Patient("222202-2203", "Morten Jakobsen", 24);
        double result = Controller.anbefaletDosisPrDøgn(patient, lægemiddel);
        assertEquals(0.048, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC2() {
        patient = new Patient("222202-2203", "Morten Jakobsen", 25);
        double result = Controller.anbefaletDosisPrDøgn(patient, lægemiddel);
        assertEquals(0.075, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC3() {
        patient = new Patient("222202-2203", "Morten Jakobsen", 126);
        double result = Controller.anbefaletDosisPrDøgn(patient, lægemiddel);
        assertEquals(0.504, result);
    }
    @Test
    void anbefaletDosisPrDøgnTC4() {
        patient = new Patient("222202-2203", "Morten Jakobsen", 0);
        double result = Controller.anbefaletDosisPrDøgn(patient, lægemiddel);
        assertEquals(null, result);
    }

}