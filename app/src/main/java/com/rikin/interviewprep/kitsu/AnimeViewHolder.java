package com.rikin.interviewprep.kitsu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.rikin.interviewprep.R;

public class AnimeViewHolder extends RecyclerView.ViewHolder {

  TextView animeTitle;
  ImageView animeCoverImage;

  public AnimeViewHolder(View itemView) {
    super(itemView);

    animeTitle = itemView.findViewById(R.id.anime_title);
    animeCoverImage = itemView.findViewById(R.id.anime_cover_image);
  }
}
