package ordination;

import controller.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    LocalDate startDato = LocalDate.of(2025, 9, 26);
    LocalDate slutDato = LocalDate.of(2025, 9, 26);
    Patient patient = new Patient("222202-2031", "Morten Jakobsen", 75);
    Lægemiddel lægemiddel = new Lægemiddel("Ibuprofen", 0.002, 0.003, 0.004, "Gram");
    Dosis[] doser = new Dosis[4];

    @org.junit.jupiter.api.Test
    void samletDosisTC1() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 26);
        doser[0] = new Dosis(LocalTime.parse("08:00"), 0);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 0);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 0);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 0);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();
        assertEquals(0, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void samletDosisTC2() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 26);
        doser[0] = new Dosis(LocalTime.parse("08:00"), 1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();
        assertEquals(4, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void samletDosisTC3() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 30);
        doser[0] = new Dosis(LocalTime.parse("08:00"), 1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 0);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();
        assertEquals(15, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void samletDosisTC4() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 26);
        doser[0] = new Dosis(LocalTime.parse("08:00"), 2);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();
        assertEquals(5, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void samletDosisTC5() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 26);
        doser[0] = new Dosis(LocalTime.parse("08:00"), -1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 0);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 0);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 0);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();
        assertEquals(-1, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void samletDosisTC6() {
        startDato = LocalDate.of(2025, 9, 26);
        slutDato = LocalDate.of(2025, 9, 25);
        doser[0] = new Dosis(LocalTime.parse("08:00"), 1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.samletDosis();

        assertEquals(4, doser.length);
    }

    @org.junit.jupiter.api.Test
    void doegnDosisTC1() {
        doser[0] = new Dosis(LocalTime.parse("08:00"), 0);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 0);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 0);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 0);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.doegnDosis();
        assertEquals(0, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void doegnDosisTC2() {
        doser[0] = new Dosis(LocalTime.parse("08:00"), 1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.doegnDosis();
        assertEquals(4, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void doegnDosisTC3() {
        doser[0] = new Dosis(LocalTime.parse("08:00"), 2);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 0);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 0);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.doegnDosis();
        assertEquals(3, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void doegnDosisTC4() {
        doser[0] = new Dosis(LocalTime.parse("08:00"), -1);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 0);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 0);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 0);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.doegnDosis();
        assertEquals(-1, result);
        assertEquals(4, doser.length);
    }
    @org.junit.jupiter.api.Test
    void doegnDosisTC5() {
        doser[0] = new Dosis(LocalTime.parse("08:00"), 2);
        doser[1] = new Dosis(LocalTime.parse("12:00"), 1);
        doser[2] = new Dosis(LocalTime.parse("18:00"), 1);
        doser[3] = new Dosis(LocalTime.parse("22:00"), 1);
        DagligFast test = new DagligFast(startDato, slutDato, doser);
        double result = test.doegnDosis();
        assertEquals(5, result);
        assertEquals(4, doser.length);
    }
}