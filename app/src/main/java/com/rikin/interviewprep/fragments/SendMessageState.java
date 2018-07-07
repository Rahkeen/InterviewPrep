package com.rikin.interviewprep.fragments;

public interface SendMessageState {

  final class Sent implements SendMessageState {
    public final String message;

    public Sent(String message) {
      this.message = message;
    }
  }
}
