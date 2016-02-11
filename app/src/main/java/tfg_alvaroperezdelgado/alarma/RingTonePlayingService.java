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


    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        mediaPlayer=MediaPlayer.create(this,R.raw.dove);
        mediaPlayer.start();



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "om Destroy called", Toast.LENGTH_SHORT).show();
    }




}
