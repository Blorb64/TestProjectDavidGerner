package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class DagligFast extends Ordination{
    Dosis[] antalDoser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Dosis[] antalDoser) {
        super(startDato, slutDato);
        this.antalDoser = antalDoser;
    }

    public Dosis[] getAntalDoser() {
        return antalDoser;
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
