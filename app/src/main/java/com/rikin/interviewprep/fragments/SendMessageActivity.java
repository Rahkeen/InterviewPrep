package com.rikin.interviewprep.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.rikin.interviewprep.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SendMessageActivity extends AppCompatActivity {

  SendFragment sendFragment;
  ReceiveFragment receiveFragment;

  SendMessageViewModel sendMessageViewModel;
  CompositeDisposable disposables = new CompositeDisposable();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_message);

    sendFragment = new SendFragment();
    receiveFragment = new ReceiveFragment();
    sendMessageViewModel = ViewModelProviders.of(this).get(SendMessageViewModel.class);

    getSupportFragmentManager().beginTransaction().add(R.id.main_container, sendFragment).commit();
  }

  @Override protected void onStop() {
    super.onStop();

    disposables.clear();
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    disposables.dispose();
  }

  @Override
  protected void onStart() {
    super.onStart();

    Disposable disposable =
        sendMessageViewModel
            .getMessages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ignored -> getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, receiveFragment)
                    .commit());

    disposables.add(disposable);
  }
}
