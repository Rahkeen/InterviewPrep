package com.rikin.interviewprep.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SleepReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    if (SleepService.ACTION_FINISHED.equals(intent.getAction())) {
      Toast.makeText(context, "WAKE UP", Toast.LENGTH_LONG).show();
    }
  }
}
