package tfg_alvaroperezdelgado.alarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by perez on 10/2/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in log (tag)","mensaje (msg)");

        //create an intent to the ringtone service
        Intent intentService= new Intent(context,RingTonePlayingService.class);

        //start the ringtone service
        context.startService(intentService);

    }
}
