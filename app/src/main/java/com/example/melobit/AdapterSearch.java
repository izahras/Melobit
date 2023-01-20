package com.example.melobit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
    List<ResultSearch> searchList;
    Context context;

    public AdapterSearch(List<ResultSearch> searchList, Context context) {
        this.searchList = searchList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context) .inflate(R.layout.view_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(searchList.get(position).getSong().getArtists().get(0).getImage().getThumbnail().getUrl())
                .into(holder.imageView);
        holder.txt_title.setText(searchList.get(position).getSong().getTitle());
        holder.txt_desk.setText(searchList.get(position).getSong().getAlbum().getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    context.startActivity(new Intent(context,PlayMusicActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("image",searchList.get(position).getSong().getArtists().get(0).getImage().getThumbnail().getUrl())
                            .putExtra("id",searchList.get(position).getSong().getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_title,txt_desk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_search);
            txt_desk=itemView.findViewById(R.id.txt_name_singer);
            txt_title=itemView.findViewById(R.id.txt_name_song);
        }
    }
}
