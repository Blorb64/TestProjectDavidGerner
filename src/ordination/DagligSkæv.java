package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class DagligSkæv extends Ordination{
    private final ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public void addAlleDoser(ArrayList<Dosis> doser) {
        this.doser.addAll(doser);
    }

    public ArrayList<Dosis> getDoser() {
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
            sum +=dosis.getAntal();
        }

        return sum;
    }

    @Override
    public String getType() {
        return String.format("Daglig skaev: startdato: %s, slutdato: %s antal doser %d"
                , super.getStartDato(), super.getSlutDato(), doser.size());
    }
}
