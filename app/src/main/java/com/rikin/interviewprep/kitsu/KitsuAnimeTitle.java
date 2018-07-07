package com.rikin.interviewprep.kitsu;

import com.squareup.moshi.Json;

class KitsuAnimeTitle {

  @Json(name = "en")
  String english;

  @Json(name = "ja_jp")
  String japanese;
}
