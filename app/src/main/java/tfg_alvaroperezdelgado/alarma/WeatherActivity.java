package tfg_alvaroperezdelgado.alarma;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


import Model.Container;
import Model.User;
import YahooWeather.data.Channel;
import YahooWeather.data.Item;
import YahooWeather.service.WeatherSerciceCallback;
import YahooWeather.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherSerciceCallback {


    private TextView tvTemperatura;
    private TextView tvCondicion;
    private TextView tvLocalizacion;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private Container container;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvTemperatura=(TextView)findViewById(R.id.tvTemperature);
        tvCondicion=(TextView)findViewById(R.id.tvCondicion);
        tvLocalizacion=(TextView)findViewById(R.id.tvLocalizacion);

        container=Container.getInstance();

        service=new YahooWeatherService(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();
        user=User.getInstance();
        service.refreshWeather(user.getZipCode().toString());




    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        //int resourceId=getResources().getIdentifier("drawable/icon" + item.getCondition().getCode(), null, getPackageName());

        // Drawable weatherIconDrawable=getResources().getDrawable(resourceId);

        //imWeatherIcon.setDrawable(weatherIconDrawable);


        tvTemperatura.setText(item.getCondition().getTemperature()+" "+channel.getUnits().getTemperature());
        tvCondicion.setText(item.getCondition().getDescription());
        tvLocalizacion.setText(service.getLocation());
        container.setWeatherString("En " + tvLocalizacion.getText().toString() + ", hace una temperatura de " +
                tvTemperatura.getText().toString() + channel.getUnits().getTemperature().toString()+ "el tiempo esta"+
                item.getCondition().getDescription().toString());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
