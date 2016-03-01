package tfg_alvaroperezdelgado.alarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class Days extends AppCompatActivity {

    //Button accept
    private Button btAceptar;
    //objet alarm
    private Alarm alarm;
    private enum week{Mon, tue, wed,thu,fri,sat,sun};

    private Switch swMon,swTue,swWed,swThu,swFri,swSat,swSun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        //get id switches
        swMon=(Switch)findViewById(R.id.swMonday);
        swTue=(Switch)findViewById(R.id.swTuesday);
        swWed=(Switch)findViewById(R.id.swWednesday);
        swThu=(Switch)findViewById(R.id.swThursday);
        swFri=(Switch)findViewById(R.id.swFriday);
        swSat=(Switch)findViewById(R.id.swSaturday);
        swSun=(Switch)findViewById(R.id.swSunday);

        //get instance Alarm
        alarm=Alarm.getInstance();



        findViewById(R.id.btAcceptDays).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //primero borramos la lista anterior de dias
                alarm.deleteListDays();
                //añadimos el valor que tenga cada dia
                alarm.setAlarmDays(1,swMon.isChecked());
                alarm.setAlarmDays(2,swTue.isChecked());
                alarm.setAlarmDays(3,swWed.isChecked());
                alarm.setAlarmDays(4,swThu.isChecked());
                alarm.setAlarmDays(5,swFri.isChecked());
                alarm.setAlarmDays(6,swSat.isChecked());
                alarm.setAlarmDays(7,swSun.isChecked());



                startActivity(new Intent(Days.this, addAlarm.class));
            }
        });
    }
}
