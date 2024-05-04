package entities;
import enums.TipoPeriodicita;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "riviste")
@PrimaryKeyJoinColumn(name = "isbn")
public class Rivista extends ElementoCatalogo {


    @Column(name = "tipo_periodicita")
    private TipoPeriodicita tipoPeriodicita;

    public Rivista(UUID isbn, String titolo, Integer anno, Integer pagine, TipoPeriodicita tipoPeriodicita) {
        super(isbn, titolo, anno, pagine);
        this.tipoPeriodicita = tipoPeriodicita;
    }

    public Rivista(){

    }

    public TipoPeriodicita getTipoPeriodicita() {
        return tipoPeriodicita;
    }

    public void setTipoPeriodicita(TipoPeriodicita tipoPeriodicita) {
        this.tipoPeriodicita = tipoPeriodicita;
    }

    @Override
    public String toString() {
        return super.toString()  +
                ", tipoPeriodicita=" + tipoPeriodicita;
    }
}

