package com.rikin.interviewprep.kitsu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.rikin.interviewprep.R;
import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeViewHolder> {

  private List<KitsuAnime> animeData;

  @NonNull
  @Override
  public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View animeView =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recyclerview_anime_item, parent, false);

    return new AnimeViewHolder(animeView);
  }

  @Override
  public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
    KitsuAnime anime = animeData.get(position);

    holder.animeTitle.setText(anime.attributes.titles.japanese);
    KitsuAnimeImage coverImage = anime.attributes.posterImage;
    if (coverImage != null) {
      Glide.with(holder.itemView).load(coverImage.small).into(holder.animeCoverImage);
    }
  }

  public void setData(List<KitsuAnime> data) {
    animeData = data;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (animeData == null) return 0;
    return animeData.size();
  }
}
