package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination{
    private final double antalEnheder;
    private ArrayList<LocalDate> datoerAnvendDosis = new ArrayList<>();

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
        datoerAnvendDosis.add(dato);
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {
        return datoerAnvendDosis.size();
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalGangeAnvendt();
    }

    @Override
    public double doegnDosis() {
        LocalDate firstDay = datoerAnvendDosis.getFirst();
        LocalDate lastDay = datoerAnvendDosis.getLast();
        return (antalGangeAnvendt() * this.antalEnheder) / ChronoUnit.DAYS.between(firstDay, lastDay);
    }

    @Override
    public String getType() {
        return String.format("PN: startdato: %s, slutdato: %s antal enheder %.2f"
                , super.getStartDato(), super.getSlutDato(), antalEnheder);
    }
}
