package com.rikin.interviewprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.rikin.interviewprep.background.BackgroundWorkActivity;
import com.rikin.interviewprep.fragments.SendMessageActivity;
import com.rikin.interviewprep.kitsu.KitsuActivity;
import com.rikin.interviewprep.services.ServiceActivity;

public class JourneyActivity extends AppCompatActivity {

  private Button kitsuApp;
  private Button backgroundWork;
  private Button simpleService;
  private Button fragments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_journey);
    initJourneyButtons();
  }

  private void initJourneyButtons() {
    kitsuApp = findViewById(R.id.journey_kitsu);
    backgroundWork = findViewById(R.id.journey_backround_work);
    simpleService = findViewById(R.id.journey_simple_service);
    fragments = findViewById(R.id.journey_fragments);

    kitsuApp.setOnClickListener(
        ignored -> {
          Intent kitsuIntent = new Intent(JourneyActivity.this, KitsuActivity.class);
          startActivity(kitsuIntent);
        });

    backgroundWork.setOnClickListener(
        ignored -> {
          Intent journeyIntent = new Intent(JourneyActivity.this, BackgroundWorkActivity.class);
          startActivity(journeyIntent);
        });

    simpleService.setOnClickListener(
        ignored -> {
          Intent serviceIntent = new Intent(JourneyActivity.this, ServiceActivity.class);
          startActivity(serviceIntent);
        });

    fragments.setOnClickListener(
        ignored -> {
          Intent messageFragmentIntent =
              new Intent(JourneyActivity.this, SendMessageActivity.class);
          startActivity(messageFragmentIntent);
        });
  }
}
