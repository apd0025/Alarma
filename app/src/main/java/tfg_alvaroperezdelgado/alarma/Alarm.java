package tfg_alvaroperezdelgado.alarma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by perez on 18/2/16.
 * Esta clase contendra toda la informacion que podemos tener en un objeto alarma.
 * La utilizaremos para acceder de una forma mas sencilla a nuestra informacion.
 * Esta clase sera singleton ya que solo queremos una alarma
 */

public class Alarm {
    int id=1;
    private int hour;
    private int min;
    private Calendar calendar;
    private enum week{Mon, tue, wed,thu,fri,sat,sun};
    private List <week> alarmDays=new ArrayList<week>();
    private String customMessage;
    private String ubuMailString;
    private String ubuCalendarString;
    private String twitterString;
    private String weatherString;
    private String description;
    private static Alarm alarm;

    //con este metodo obtenemos la instancia de alarm
    public static Alarm getInstance(){
        if(alarm==null){
            alarm=new Alarm();
        }
        return alarm;
    }

    public int getHour() { return hour; }

    public void setHour(int hour) { this.hour = hour; }

    public int getMin() { return min; }

    public void setMin(int min) { this.min = min; }

    public String getUbuCalendarString() { return ubuCalendarString; }

    public void setUbuCalendarString(String ubuCalendarString) { this.ubuCalendarString = ubuCalendarString; }

    public List<week> getAlarmDays() { return alarmDays; }

    public void setAlarmDays(List<week> alarmDays) { this.alarmDays = alarmDays; }

    public String getCustomMessage() { return customMessage; }

    public void setCustomMessage(String customMessage) { this.customMessage = customMessage; }

    public String getUbuMailString() {
        return ubuMailString;
    }

    public void setUbuMailString(String ubuMailString) {
        this.ubuMailString = ubuMailString;
    }

    public String getTwitterString() {
        return twitterString;
    }

    public void setTwitterString(String twitterString) {
        this.twitterString = twitterString;
    }

    public String getWeatherString() { return weatherString;}

    public void setWeatherString(String weatherString) { this.weatherString = weatherString;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
