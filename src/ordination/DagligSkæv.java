package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class DagligSkæv extends Ordination{
    ArrayList<Dosis> antalDoser = new ArrayList<>();

    public DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public void addAntalDoser(Dosis dosis) {
        this.antalDoser.add(dosis);
    }

    public ArrayList<Dosis> getAntalDoser() {
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
