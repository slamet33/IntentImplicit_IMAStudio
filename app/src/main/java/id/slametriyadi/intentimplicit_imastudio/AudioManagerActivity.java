package id.slametriyadi.intentimplicit_imastudio;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btn_ring)
    Button btnRing;
    @BindView(R.id.btn_silent)
    Button btnSilent;
    @BindView(R.id.btn_vibrate)
    Button btnVibrate;

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    }

    @OnClick({R.id.btn_ring, R.id.btn_silent, R.id.btn_vibrate})
    public void onViewClicked(View view) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        switch (view.getId()) {
            case R.id.btn_ring:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()){
                    Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivity(intent);
                }
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "Normal Mode Applied", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_silent:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()){
                    Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivity(intent);
                }
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "Silent Mode Applied", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_vibrate:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "Vibrate Mode Applied", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
