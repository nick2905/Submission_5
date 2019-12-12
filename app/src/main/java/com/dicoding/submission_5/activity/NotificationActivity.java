package com.dicoding.submission_5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.dicoding.submission_5.R;
import com.dicoding.submission_5.alarm.AlarmReceiver;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private AlarmReceiver alarmReceiver;
    private Switch sDaily, sRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        sDaily = findViewById(R.id.daily_reminder);
        sRelease = findViewById(R.id.daily_reminder_release);
        alarmReceiver = new AlarmReceiver();

        if(alarmReceiver.isAlarmHasSet(this,AlarmReceiver.DAILY_REMINDER_MOVIE)){
            sDaily.setChecked(true);
        }else{
            sDaily.setChecked(false);
        }
        if(alarmReceiver.isAlarmHasSet(this,AlarmReceiver.DAILY_REMINDER_RELEASE)){
            sRelease.setChecked(true);
        }else{
            sRelease.setChecked(false);
        }

        sDaily.setOnClickListener(this);
        sRelease.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.daily_reminder:
                if(sDaily.isChecked()){
                    String EXTRA_NOTIF = getString(R.string.heyLook);
                    alarmReceiver.setDailyAlarm(this,AlarmReceiver.DAILY_REMINDER_MOVIE, EXTRA_NOTIF);
                    Toast.makeText(this,getString(R.string.daily_has_on),Toast.LENGTH_SHORT).show();
                }else{
                    alarmReceiver.cancelAlarm(this, AlarmReceiver.DAILY_REMINDER_MOVIE);
                    Toast.makeText(this, getString(R.string.daily_has_off),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.daily_reminder_release:
                if(sRelease.isChecked()){
                    alarmReceiver.setDailyRelease(this, AlarmReceiver.DAILY_REMINDER_RELEASE);
                    Toast.makeText(this, getString(R.string.release_has_on),Toast.LENGTH_SHORT).show();
                }else{
                    alarmReceiver.cancelAlarm(this, AlarmReceiver.DAILY_REMINDER_RELEASE);
                    Toast.makeText(this, getString(R.string.release_has_off),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
