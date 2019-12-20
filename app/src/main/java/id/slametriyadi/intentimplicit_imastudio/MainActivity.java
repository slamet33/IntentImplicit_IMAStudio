package id.slametriyadi.intentimplicit_imastudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_audio_manager)
    Button btnAudioManager;
    @BindView(R.id.btn_notification)
    Button btnNotification;
    @BindView(R.id.btn_wifi)
    Button btnWifi;
    @BindView(R.id.btn_email)
    Button btnEmail;
    @BindView(R.id.btn_sms)
    Button btnSms;
    @BindView(R.id.btn_telepon)
    Button btnTelepon;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_browser)
    Button btnBrowser;
    @BindView(R.id.btn_alarm)
    Button btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_audio_manager, R.id.btn_notification, R.id.btn_wifi, R.id.btn_email, R.id.btn_sms, R.id.btn_telepon, R.id.btn_camera, R.id.btn_browser, R.id.btn_alarm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_audio_manager:
                break;
            case R.id.btn_notification:
                break;
            case R.id.btn_wifi:
                break;
            case R.id.btn_email:
                break;
            case R.id.btn_sms:
                break;
            case R.id.btn_telepon:
                break;
            case R.id.btn_camera:
                break;
            case R.id.btn_browser:
                break;
            case R.id.btn_alarm:
                startActivity(new Intent(this, AlarmActivity.class));
                break;
        }
    }
}
