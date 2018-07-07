package com.rikin.interviewprep.services;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rikin.interviewprep.R;

public class ServiceActivity extends AppCompatActivity {

  private Intent sleepIntent;
  private SleepReceiver sleepReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service);

    sleepIntent = new Intent(this, SleepService.class);
    sleepIntent.putExtra(SleepService.EXTRA_SLEEP_TIME, 4000);

    sleepReceiver = new SleepReceiver();
  }

  @Override
  protected void onStart() {
    super.onStart();
    registerReceiver(sleepReceiver, new IntentFilter(SleepService.ACTION_FINISHED));
    startService(sleepIntent);
  }

  @Override
  protected void onStop() {
    super.onStop();
    unregisterReceiver(sleepReceiver);
  }
}
