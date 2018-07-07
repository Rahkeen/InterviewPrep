package com.rikin.interviewprep.background;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ProgressBar;

public class ProgressHandler extends Handler {

  public static final int PROGRESS_UPDATE = 1;
  public static final int PROGRESS_MAX = 2;
  public static final String BUNDLE_PROGRESS_VALUE = "ProgressHandler_progress_value";
  public static final String BUNDLE_PROGRESS_MAX = "ProgressHandler_progress_max";

  private final ProgressBar progressBar;

  public ProgressHandler(Looper looper, ProgressBar progressBar) {
    super(looper);
    this.progressBar = progressBar;
  }

  @Override
  public void handleMessage(Message msg) {
    if (msg.what == PROGRESS_UPDATE) {
      int progress = msg.getData().getInt(BUNDLE_PROGRESS_VALUE);
      progressBar.setProgress(progress);
    } else if (msg.what == PROGRESS_MAX) {
      int max = msg.getData().getInt(BUNDLE_PROGRESS_MAX);
      progressBar.setMax(max);
    }
  }
}
