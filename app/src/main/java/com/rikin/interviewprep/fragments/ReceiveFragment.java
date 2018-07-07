package com.rikin.interviewprep.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rikin.interviewprep.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.w3c.dom.Text;

public class ReceiveFragment extends Fragment {

  private TextView receivedMessage;

  private CompositeDisposable disposables;
  private SendMessageViewModel sendMessageViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    disposables = new CompositeDisposable();
    sendMessageViewModel = ViewModelProviders.of(requireActivity()).get(SendMessageViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_receive_message, container, false);
    initViews(view);
    return view;
  }

  @Override public void onStart() {
    super.onStart();

    subscribeToMessages();
  }

  @Override public void onStop() {
    super.onStop();

    disposables.clear();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    disposables.dispose();
  }

  private void subscribeToMessages() {
    Disposable disposable =
        sendMessageViewModel
            .getMessages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::render);

    disposables.add(disposable);
  }

  private void render(@NonNull String state) {
    receivedMessage.setText(state);
  }

  private void initViews(View view) {
    receivedMessage = view.findViewById(R.id.text_message);
  }
}
