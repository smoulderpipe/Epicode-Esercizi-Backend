package adapter;

import java.util.Calendar;
import java.util.Date;

public class InfoAdapter implements DataSource {

    private Info info;
    public InfoAdapter(Info info){
        this.info = info;
    }

    @Override
    public String getNomeCompleto() {
        return info.getNome() + " " + info.getCognome();
    }

    @Override
    public int getEta() {
        Date currentDate = new Date();
        long ageInMillis = currentDate.getTime() - info.getDataDiNascita().getTime();
        Calendar ageCalendar = Calendar.getInstance();
        ageCalendar.setTimeInMillis(ageInMillis);
        return ageCalendar.get(Calendar.YEAR) - 1970;
    }
}
