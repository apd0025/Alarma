package tfg_alvaroperezdelgado.alarma;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Alvaro Perez on 10/2/16.
 */
public class RingTonePlayingService extends Service {

    //Object which play a song
    MediaPlayer mediaPlayer;

    //It controls what button is push
    int start_id;
    //controla si esta funcionando esta clase
    boolean isRunning;

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

            Log.e("There is no music, ","and you want start");

            //creamos el media player pasandole el contexto y el audio que queremos escuchar
            mediaPlayer=MediaPlayer.create(this,R.raw.dove);
            //Start the ringtone
            mediaPlayer.start();


            this.isRunning=true;
            this.start_id=0;

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
