package ordination;

import java.time.LocalDate;

public class PN extends Ordination{
    private final double antalEnheder;

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {

    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {

        return -1;
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double døgnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "";
    }
}
