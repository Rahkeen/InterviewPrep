package com.rikin.interviewprep.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.rikin.interviewprep.R;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SendFragment extends Fragment {

  private EditText editMessage;
  private Button sendMessage;

  private SendMessageViewModel sendMessageViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    sendMessageViewModel = ViewModelProviders.of(requireActivity()).get(SendMessageViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_send_message, container, false);
    initViews(view);
    return view;
  }

  private void initViews(View view) {
    editMessage = view.findViewById(R.id.edit_send_message);
    sendMessage = view.findViewById(R.id.btn_send_message);
    sendMessage.setOnClickListener(ignored -> {
      sendMessageViewModel.sendMessage(editMessage.getText().toString());
    });
  }
}
