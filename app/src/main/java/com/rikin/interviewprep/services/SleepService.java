package com.rikin.interviewprep.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class SleepService extends IntentService {

  public static final String EXTRA_SLEEP_TIME = "SleepService_sleep_time";
  public static final String ACTION_FINISHED = "SleepService_finished";

  public SleepService() {
    super("SleepService");
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    assert intent != null;
    long sleepTime = intent.getLongExtra(EXTRA_SLEEP_TIME, 5000L);

    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Intent finishedIntent = new Intent(ACTION_FINISHED);
    sendBroadcast(finishedIntent);
  }
}
