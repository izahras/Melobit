package com.example.melobit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uni.melobit.ArtistModels.ResultArtists;
import com.uni.melobit.SongModels.ResultSongs;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSongs extends RecyclerView.Adapter<AdapterSongs.ViewHolder> {
    Context context;
    List<ResultSongs> songsModels;
    List<ResultArtists> artistsModels;

    public AdapterSongs(Context context, List<ResultSongs> songsModels, List<ResultArtists> artistsModels) {
        this.context = context;
        this.songsModels = songsModels;
        this.artistsModels = artistsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context) .inflate(R.layout.view_item_recy_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (songsModels==null){
            Glide.with(context)
                    .load(artistsModels.get(position).getImage().getThumbnail().getUrl())
                    .into(holder.imageView);
            holder.txt_title.setText(artistsModels.get(position).getFullName());
            holder.txt_desk.setText("");
        }else {
            Glide.with(context)
                    .load(songsModels.get(position).getArtists().get(0).getImage().getThumbnail().getUrl())
                    .into(holder.imageView);
            holder.txt_title.setText(songsModels.get(position).getTitle());
            holder.txt_desk.setText(songsModels.get(position).getArtists().get(0).getFullName());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songsModels!=null) {
                    context.startActivity(new Intent(context,PlayMusicActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("image",songsModels.get(position).getArtists().get(0).getImage().getThumbnail().getUrl())
                            .putExtra("id",songsModels.get(position).getId()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (songsModels==null)
        return artistsModels.size();
        else
            return  songsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_title,txt_desk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgsongs);
            txt_desk=itemView.findViewById(R.id.txt_desc);
            txt_title=itemView.findViewById(R.id.txt_title_song);
        }
    }
}
