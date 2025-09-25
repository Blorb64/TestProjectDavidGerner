package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination{
    private final Dosis[] doser;

    public DagligFast(LocalDate startDato, LocalDate slutDato, Dosis[] doser) {
        super(startDato, slutDato);
        this.doser = doser;
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public double doegnDosis() {
        double sum = 0;

        for (Dosis dosis : doser) {
            sum += dosis.getAntal();
        }

        return sum;
    }

    @Override
    public String getType() {
        return String.format("Daglig fast: startdato: %s, slutdato: %s antal doser %d"
                , super.getStartDato(), super.getSlutDato(), doser.length);
    }
}
