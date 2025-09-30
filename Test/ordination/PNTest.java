package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    private PN pn;

    @BeforeEach
    void opretPn(){
        pn = new PN(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 28), 30);
    }


    //Test af anvendDosis() og antalGangeAnvendt()
    @Test
    void antalGangeAnvendtOgAnvendDosis() {
        //Arrange in setup

        //act
        int antalAnvendt = pn.antalGangeAnvendt();

        //assert
        assertEquals(0, antalAnvendt);

        //act
        pn.anvendDosis(LocalDate.of(2025, 10, 26));
        antalAnvendt = pn.antalGangeAnvendt();

        //assert
        assertEquals(1, antalAnvendt);

        //act
        pn.anvendDosis(LocalDate.of(2025, 10, 27));
        antalAnvendt = pn.antalGangeAnvendt();

        //assert
        assertEquals(2, antalAnvendt);
    }

    //DoegnDosis
    @Test
    void doegnDosisAnvendOpTil3ForskelligeGangeOgAntalStor() {
        //Arrange in setup

        //act
        double doegnDosis = pn.doegnDosis();

        //assert
        assertEquals(0, doegnDosis);

        //Arrange
        pn.anvendDosis(LocalDate.of(2025, 10, 21));

        //act
        doegnDosis = pn.doegnDosis();

        //assert
        assertEquals(30, doegnDosis);

        //arrange
        pn.anvendDosis(LocalDate.of(2025, 10, 27));
        pn.anvendDosis(LocalDate.of(2025, 10, 28));

        //act
        doegnDosis = pn.doegnDosis();

        //assert
        assertEquals(11.25, doegnDosis);
    }

    @Test
    void doegnDosisAnvend3GangeSammeDagOgAntalStor() {
        //arrange startet i beforeEach
        pn.anvendDosis(LocalDate.of(2025, 10, 26));
        pn.anvendDosis(LocalDate.of(2025, 10, 26));
        pn.anvendDosis(LocalDate.of(2025, 10, 26));

        //act
        double doegnDosis = pn.doegnDosis();

        //assert
        assertEquals(90, doegnDosis);
    }

    @Test
    void doegnDosisAnvendOpTil3ForskelligeGangeOgGraenserNul() {
        //arrange
        pn = new PN(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 28), 0);

        //act
        double doegndosis = pn.doegnDosis();

        //assert
        assertEquals(0, doegndosis);

        //arrange
        pn.anvendDosis(LocalDate.of(2025,10,21));

        //act
        doegndosis = pn.doegnDosis();

        //assert
        assertEquals(0, doegndosis);

        //arrange
        pn.anvendDosis(LocalDate.of(2025,10,27));
        pn.anvendDosis(LocalDate.of(2025,10,28));

        //act
        doegndosis = pn.doegnDosis();

        //assert
        assertEquals(0, doegndosis);
    }

    @Test
    void doegnDosisAnvend3GangeSammeDagOgGraenserNul() {
        //arrange startet i beforeEach
        pn =  new PN(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 10, 28), 0);
        pn.anvendDosis(LocalDate.of(2025, 10, 26));
        pn.anvendDosis(LocalDate.of(2025, 10, 26));
        pn.anvendDosis(LocalDate.of(2025, 10, 26));

        //act
        double doegnDosis = pn.doegnDosis();

        //assert
        assertEquals(0, doegnDosis);
    }

    @Test
    void samletDosisAnvendOpTilTreGange() {
        //arrange i beforeEach

        //act
        double samletDosis = pn.samletDosis();

        //assert
        assertEquals(0, samletDosis);

        //arrange
        pn.anvendDosis(LocalDate.of(2025, 10, 21));

        //act
        samletDosis = pn.samletDosis();

        //assert
        assertEquals(30, samletDosis);

        //arrange
        pn.anvendDosis(LocalDate.of(2025, 10, 27));
        pn.anvendDosis(LocalDate.of(2025, 10, 28));

        //act
        samletDosis = pn.samletDosis();

        //assert
        assertEquals(33.75, samletDosis);
    }
}