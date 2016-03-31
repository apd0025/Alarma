package tfg_alvaroperezdelgado.alarma;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import Model.Container;

/**
 * Esta clase se ocupa de reproducir el sonido de la alarma
 */
public class RingTonePlayingService extends Service {

    //mediaPlat
    private MediaPlayer mediaPlayer;
    //start_id controla que boton esta pulsado
    private int start_id;
    //isRunning controla si esta funcionando esta clase
    private boolean isRunning;
    //container contiene lo que queremos leer
    Container container;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int start_id) {
        Log.e("RingtonePlayingService", ", OnStartCommand");
        Log.i("LocalService", "Received start id " + start_id + ": " + intent);

        //fetch the extra string from the intent
        String state=intent.getExtras().getString("extra");


        Log.e("Ringtone state:extra is",state);





        //comprobar que state no tenga null
        assert state !=null;

        //change the id in function extra string
        switch (state) {
            case "alarm on":
                start_id = 1;
                break;
            case "alarm off":
                start_id = 0;
                break;
            default:
                start_id = 0;
                break;
        }


        //if there is no music playing an the user pressed "alarm on"
        //music should start playing
        if(!this.isRunning&& start_id==1){
            //obtenemos una instancia de container
            container=Container.getInstance();
            Log.e("There is no music, ", "and you want start");

            //si hay algo en el song, o es distinto de nulo que lea esa cancion.
            if(!(container.getSong()==null)) {
                //creamos una uri a traves de nuestro archivo File cancion para pasarselo al mediaPlayer
                Uri u = Uri.parse(container.getSong().toString());

                //creamos el media player pasandole el contexto y el audio que queremos escuchar
                mediaPlayer = MediaPlayer.create(this, u);
            }//sino que ponga una cancion por defecto
            else {
                //cancion por defecto
                mediaPlayer = MediaPlayer.create(this, R.raw.dove);
            }

            //Start the ringtone
            mediaPlayer.start();
            //Como iniciar una actividad desde aqui
            //startActivity(new Intent(RingTonePlayingService.this, AddMessageUser.class));

            this.isRunning=true;
            this.start_id=0;

            //notification
            //set up the notification service
            NotificationManager notificationManager=(NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            //set up the intent that goes to the Main Activity
            Intent intentMainActivity=new Intent(this.getApplicationContext(),MainActivity.class);

            //set up pending intent
            PendingIntent pendingIntentMainActivity=PendingIntent.getActivity(this,0,intentMainActivity,0);


            //make the notification parameters
            //TODO poner icono y contenido de los mensajes y mirar lo del build y que se muestre bajando y tambien mirar lo de lapi
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                Notification notificationPopup= null;

                notificationPopup = new Notification.Builder(this)
                        .setContentTitle("Alarma!!")
                        .setContentText("alarma")
                        .setContentIntent(pendingIntentMainActivity)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();


            //set up the notification start command
            notificationManager.notify(0,notificationPopup);
            }

        }
        //if there is music playing, and the usser pressed "alarm off"
        //music should stop playing
        else if(this.isRunning&&start_id==0){
            Log.e("There is music, ","and you want end");

            //Stop the ringtone
            mediaPlayer.stop();

            mediaPlayer.reset();


            this.isRunning=false;
            this.start_id=0;


        }
        //music is not playing and press "alarm off"
        // you prove the alarm
        //do nothing
        else if(!this.isRunning&&start_id==0){
            Log.e("There is no music, ","and you want end");

            this.isRunning=false;
            this.start_id=0;
        }
        //if the music is playing and the user pressed "alarm on"
        //do nothing
        else if(this.isRunning&&start_id==1){
            Log.e("There is music, ","and you want start");

            this.isRunning=true;
            this.start_id=1;
        }
        //cant think of anything else, just to catch the odd event
        else {
            Log.e("Else  ","somehow");
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("RingTonePlayingService",", onDestroy");

        super.onDestroy();
        this.isRunning=false;
    }




}
