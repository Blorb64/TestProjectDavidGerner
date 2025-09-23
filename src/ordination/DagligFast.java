package ordination;

import java.time.LocalDate;

public class DagligFast extends Ordination{
    Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Dosis[] antalDoser) {
        super(startDato, slutDato);
        this.doser = antalDoser;
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "";
    }
}
