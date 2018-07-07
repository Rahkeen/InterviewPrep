package com.rikin.interviewprep.background;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import com.rikin.interviewprep.R;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BackgroundWorkActivity extends AppCompatActivity {

  ProgressBar progressBar;
  Button downloadButton;

  ProgressHandler progressHandler;
  Thread downloadThread;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_background_work);

    progressBar = findViewById(R.id.download_progress);
    downloadButton = findViewById(R.id.download_progress_button);

    downloadThread =
        new Thread() {
          @Override
          public void run() {
            OkHttpClient client = new OkHttpClient();
            Call call =
                client.newCall(
                    new Request.Builder().get().url("https://i.imgur.com/hVmHR4X.jpg").build());
            try {
              Response response = call.execute();
              if (response.body() != null) {
                InputStream stream = response.body().byteStream();

                // set the Max on the progress bar
                Message setMax = progressHandler.obtainMessage(ProgressHandler.PROGRESS_MAX);
                Bundle dataBundle = new Bundle();
                long total = 0;
                int max = (int) response.body().contentLength();
                dataBundle.putInt(ProgressHandler.BUNDLE_PROGRESS_MAX, max);
                setMax.setData(dataBundle);
                progressHandler.sendMessage(setMax);

                while (true) {
                  int read = stream.read();
                  if (read < 0) {
                    break;
                  }
                  total += read;
                  Message setProgress =
                      progressHandler.obtainMessage(ProgressHandler.PROGRESS_UPDATE);
                  Bundle progressBundle = new Bundle();
                  progressBundle.putInt(ProgressHandler.BUNDLE_PROGRESS_VALUE, (int) total);
                  setProgress.setData(progressBundle);
                  progressHandler.sendMessage(setProgress);
                }

                stream.close();
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        };

    downloadButton.setOnClickListener(ignored -> downloadThread.start());
  }

  @Override
  protected void onStart() {
    progressHandler = new ProgressHandler(getMainLooper(), progressBar);

    super.onStart();
  }
}
