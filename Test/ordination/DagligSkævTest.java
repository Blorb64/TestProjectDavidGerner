package ordination;

import jdk.jshell.execution.LoaderDelegate;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkævTest {
    private DagligSkæv dagligSkæv = new DagligSkæv(LocalDate.parse("2025-11-11"), LocalDate.parse("2025-11-18"));
    private LocalDate startDato = LocalDate.parse("2025-11-11");
    private LocalDate slutDato = LocalDate.parse("2025-11-18");

    @Test
    void doegnDosisTC1() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(new Dosis(LocalTime.parse("08:00"), 0)));
        dagligSkæv.addAlleDoser(doser);
        double TC1 = dagligSkæv.doegnDosis();
        assertEquals(0, TC1, 0.0001);

    }

    @Test
    void doegnDosisTC2() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0.5),
                new Dosis(LocalTime.parse("11:00"), 1),
                new Dosis(LocalTime.parse("14:00"), 1),
                new Dosis(LocalTime.parse("17:00"), .5),
                new Dosis(LocalTime.parse("20:00"), 2),
                new Dosis(LocalTime.parse("23:00"), 1)
                ));
        dagligSkæv.addAlleDoser(doser);
        double TC2 = dagligSkæv.doegnDosis();
        assertEquals(6, TC2, 0.0001);
    }

    @Test
    void doegnDosisTC3() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(new Dosis(LocalTime.parse("08:00"), 0.1)));
        dagligSkæv.addAlleDoser(doser);
        double TC3 = dagligSkæv.doegnDosis();
        assertEquals(0.1, TC3, 0.0001);
    }

    @Test
    void doegnDosisTC4() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0),
                new Dosis(LocalTime.parse("11:00"), 0),
                new Dosis(LocalTime.parse("14:00"), 0)
        ));
        dagligSkæv.addAlleDoser(doser);
        double TC4 = dagligSkæv.doegnDosis();
        assertEquals(0, TC4, 0.0001);
    }

    @Test
    void doegnDosisTC5() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(new Dosis(LocalTime.parse("08:00"), -1)));
        dagligSkæv.addAlleDoser(doser);
        double TC5 = dagligSkæv.doegnDosis();
        assertEquals(-1, TC5, 0.0001);
    }

    @Test
    void doegnDosisTC6() {
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0.5),
                new Dosis(LocalTime.parse("11:00"), 1),
                new Dosis(LocalTime.parse("14:00"), -3)
        ));
        dagligSkæv.addAlleDoser(doser);
        double TC6 = dagligSkæv.doegnDosis();
        assertEquals(-1.5, TC6, 0.0001);
    }

    @Test
    void samletDosisTC1() {
        DagligSkæv dagligSkævTC = new DagligSkæv(startDato, LocalDate.parse("2025-11-11"));
        ArrayList<Dosis> doserTC1 = new ArrayList<>(List.of(new Dosis(LocalTime.parse("08:00"), 0.1)));
        dagligSkævTC.addAlleDoser(doserTC1);
        double TC1 = dagligSkævTC.samletDosis();
        assertEquals(0.1, TC1, 0.0001);
    }

    @Test
    void samletDosisTC2() {
        DagligSkæv dagligSkævTC = new DagligSkæv(startDato, slutDato);
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0.5),
                new Dosis(LocalTime.parse("11:00"), 1),
                new Dosis(LocalTime.parse("14:00"), 1),
                new Dosis(LocalTime.parse("17:00"), .5),
                new Dosis(LocalTime.parse("20:00"), 2),
                new Dosis(LocalTime.parse("23:00"), 1)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC2 = dagligSkævTC.samletDosis();
        assertEquals(48, TC2, 0.0001);
    }

    @Test
    void samletDosisTC3() {
        DagligSkæv dagligSkævTC = new DagligSkæv(startDato, slutDato);
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0),
                new Dosis(LocalTime.parse("11:00"), 0),
                new Dosis(LocalTime.parse("14:00"), 0)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC3 = dagligSkævTC.samletDosis();
        assertEquals(0, TC3, 0.0001);
    }

    @Test
    void samletDosisTC4() {
        DagligSkæv dagligSkævTC = new DagligSkæv(startDato, slutDato);
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 1),
                new Dosis(LocalTime.parse("11:00"), 1),
                new Dosis(LocalTime.parse("14:00"), 1)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC3 = dagligSkævTC.samletDosis();
        assertEquals(24, TC3, 0.0001);
    }

    @Test
    void samletDosisTC5() {
        DagligSkæv dagligSkævTC = new DagligSkæv(slutDato, startDato);
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 1),
                new Dosis(LocalTime.parse("11:00"), 1),
                new Dosis(LocalTime.parse("14:00"), 1)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC5 = dagligSkævTC.samletDosis();
        assertEquals(-18, TC5, 0.0001);
    }

    @Test
    void samletDosisTC6() {
        DagligSkæv dagligSkævTC = new DagligSkæv(startDato, LocalDate.parse("2025-11-14"));
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), -1)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC6 = dagligSkævTC.samletDosis();
        assertEquals(-4, TC6, 0.0001);
    }

    @Test
    void samletDosisTC7() {
        DagligSkæv dagligSkævTC = new DagligSkæv(LocalDate.parse("2025-11-12"), LocalDate.parse("2025-11-11"));
        ArrayList<Dosis> doser = new ArrayList<>(List.of(
                new Dosis(LocalTime.parse("08:00"), 0)
        ));
        dagligSkævTC.addAlleDoser(doser);
        double TC7 = dagligSkævTC.samletDosis();
        assertEquals(0, TC7, 0.0001);
    }
}