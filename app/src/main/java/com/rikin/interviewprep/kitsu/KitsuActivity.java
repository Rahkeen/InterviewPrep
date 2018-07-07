package com.rikin.interviewprep.kitsu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.rikin.interviewprep.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class KitsuActivity extends AppCompatActivity {

  private static final String KITSU_BASE_URL = "https://kitsu.io/api/edge/";

  private final CompositeDisposable disposables = new CompositeDisposable();

  private KitsuService kitsuApi;

  private RecyclerView animeList;
  private AnimeListAdapter animeListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_interview);

    setupKitsuApi();
    setupAnimeList();
  }

  private void setupAnimeList() {
    animeList = findViewById(R.id.anime_list);
    animeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    animeListAdapter = new AnimeListAdapter();
    animeList.setAdapter(animeListAdapter);
  }

  private void setupKitsuApi() {
    Retrofit retrofit =
        new Retrofit.Builder()
            .client(new OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(KITSU_BASE_URL)
            .build();

    kitsuApi = retrofit.create(KitsuService.class);
  }

  @Override
  protected void onStart() {
    super.onStart();

    Disposable animeDisposable =
        kitsuApi
            .getAnime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                kitsuResponse -> animeListAdapter.setData(kitsuResponse.data),
                throwable -> Log.e(getLocalClassName(), throwable.getMessage()));

    disposables.add(animeDisposable);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    disposables.clear();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    disposables.dispose();
  }
}
