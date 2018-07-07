package com.rikin.interviewprep.kitsu;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface KitsuService {

    @GET("anime")
    Single<KitsuResponse> getAnime();
}
