package controller;

import ordination.Lægemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestGerner {
    //basic Lægemiddel
    private static Lægemiddel lægemiddel;
    //patient bruges specifik vægt
    private static Patient patient;

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

    void opretPNOrdinationSlutDatoFoerStartDato() {
        //arrange sker før

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Controller.opretPNOrdination(LocalDate.of(2025, 10, 20),
                LocalDate.of(2025, 9, 28), patient, lægemiddel,30));

        assertEquals("Startdato kan ikke være efter slutdato", exception.toString());

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