package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
    private final LocalDate startDato;
    private final LocalDate slutDato;
    private Lægemiddel lægemiddel;


    public Ordination(LocalDate startDato, LocalDate slutDato) {
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Returner antal hele dage mellem startdato og slutdato
     * (begge dage inklusive).
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    public Lægemiddel getLægemiddel() {
        return lægemiddel;
    }

    public void setLægemiddel(Lægemiddel lægemiddel) {
        this.lægemiddel = lægemiddel;
    }

    @Override
    public String toString() {
        return startDato.toString();
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    public abstract double samletDosis();

    /** Returner den gennemsnitlige dosis givet per dag. */
    public abstract double døgnDosis();

    /** Returner ordinationstypen som en String. */
    public abstract String getType();
}
