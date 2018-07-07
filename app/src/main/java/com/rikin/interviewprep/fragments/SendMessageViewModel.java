package com.rikin.interviewprep.fragments;

import android.arch.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class SendMessageViewModel extends ViewModel {

  private BehaviorSubject<String> messages = BehaviorSubject.create();

  public Observable<String> getMessages() {
    return  messages;
  }

  public void sendMessage(String message) {
    messages.onNext(message);
  }
}
