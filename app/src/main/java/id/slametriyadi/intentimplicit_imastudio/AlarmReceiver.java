package id.slametriyadi.intentimplicit_imastudio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

//
// Broadcast Receiver merupakan salah satu
// komponen Android yang bertugas dalam penerima pesan
// dari sistem atau sebuah aplikasi, bentuk pesan yang dikirim ini bisa berupa event atau invent.

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm berbunyi", Toast.LENGTH_SHORT).show();
        MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
        player.start();
    }
}
