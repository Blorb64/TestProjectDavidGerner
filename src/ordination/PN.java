package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination{
    private final double antalEnheder;
    private final ArrayList<LocalDate> datoerAnvendDosis = new ArrayList<>();

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
        if (datoerAnvendDosis.isEmpty()) return 0;

        LocalDate firstDay = datoerAnvendDosis.getFirst();
        LocalDate lastDay = datoerAnvendDosis.getLast();

        for (LocalDate dato : datoerAnvendDosis) {
            if(dato.isBefore(firstDay)){
                firstDay = dato;
            }
            if(dato.isAfter(lastDay)) {
                lastDay = dato;
            }
        }

        return (antalGangeAnvendt() * this.antalEnheder) / (ChronoUnit.DAYS.between(firstDay, lastDay) + 1);
    }

    @Override
    public String getType() {
        return String.format("PN: startdato: %s, slutdato: %s antal enheder %.2f"
                , super.getStartDato(), super.getSlutDato(), antalEnheder);
    }
}
