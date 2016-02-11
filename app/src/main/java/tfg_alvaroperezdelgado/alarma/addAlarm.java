package tfg_alvaroperezdelgado.alarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class addAlarm extends AppCompatActivity {
    //alarmManager
    AlarmManager alarmManager;

    //time picker como obtendremos la hora de la alarma
    TimePicker alarmTimePicker;

    //texto del medio
    TextView tvUpdateText;

    Context context;

    //Create a pendidgIntent that delays the intent
    //until the specified calendar time
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        this.context=this;

        //inicialize our alarmManager
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        //inicialize our timePicker
        alarmTimePicker=(TimePicker) findViewById(R.id.tmTimePicker);

        //inicialize text update box
        tvUpdateText=(TextView)findViewById(R.id.tvUpdateText);

        //create an instance of a calendar
        final Calendar calendar=Calendar.getInstance();


        //create an intent to the alarm receiver class
        //tells which receiver to send signal to
        final Intent intent= new Intent(this.context,AlarmReceiver.class);

        //inicialice buttons
        Button startAlarm=(Button)findViewById(R.id.btAcceptAdd);

        findViewById(R.id.btDiasAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addAlarm.this, Days.class));
            }
        });
        findViewById(R.id.btCancelAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addAlarm.this, MainActivity.class));
            }
        });

        //create onClick listener to start the alarm
        startAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //estamos asociando a nuestro calendario a traves del timePicker la hora y el dia
                //setting calendar instance with the Hour and minute we picked
                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getMinute());

                //get the string values of the hour and minute
                //TODO
                int hour=alarmTimePicker.getHour();
                int minute=alarmTimePicker.getMinute();

                //Convert the String values to string
                String stringHour=String.valueOf(hour);
                String stringMinute=String.valueOf(minute);

                //convertir a 12 hour time
                if(hour>12){
                    stringHour=String.valueOf(hour-12);
                }

                //convertir minuto 10:7 a 10:07
                if(minute<10){
                    stringMinute=String.valueOf("0"+minute);

                }

                //method that changes the updatetext Textbox
                setAlarmText("Alarm set to "+stringHour+" : "+stringMinute);

                //Create a pendidgIntent that delays the intent
                //until the specified calendar time
                //tels the alarm manager to send a delayed intent
                pendingIntent=PendingIntent.getBroadcast(addAlarm.this,0,
                        intent,PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                //le pasamos el reloj de tiempo real, el calendario que contiene la hora y minuto a los que vamos a poner la alarma
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()
                        ,pendingIntent);

            }
        });


        //inicialice stopButton
        Button stopAlarm=(Button)findViewById(R.id.btCancelAdd);

        //create onClick listener to stop the alarm
        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //method that changes the updatetext Textbox
                //setAlarmText("Alarm off!");

                //cancel the alarm
                alarmManager.cancel(pendingIntent);

            }
        });





    }

    //metodo que cambia el texto de dentro de un text view
    private void setAlarmText(String s) {
       tvUpdateText.setText(s);

    }


}