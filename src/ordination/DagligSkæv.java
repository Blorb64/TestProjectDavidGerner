package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class DagligSkæv extends Ordination{
    ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public void addAntalDoser(Dosis dosis) {
        this.doser.add(dosis);
    }

    public ArrayList<Dosis> getDoser() {
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
