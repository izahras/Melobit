package com.example.melobit;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.uni.melobit.ArtistModels.ArtistsModel;
import com.uni.melobit.ArtistModels.ResultArtists;
import com.uni.melobit.SongModels.ResultSongs;
import com.uni.melobit.SongModels.SongsModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListSongsActivity extends AppCompatActivity {
    RetrofitInterface retrofitInterface;
    RecyclerView recyclerView;
    AdapterSongs adapterSongs;
    List<ResultSongs> resultSongsList;
    List<ResultArtists> resultArtistsList;
    String title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        title=getIntent().getStringExtra("title");
        resultSongsList=new ArrayList<>();
        resultArtistsList=new ArrayList<>();

        recyclerView=findViewById(R.id.recysongs);
        ((TextView)findViewById(R.id.txtTitle)).setText(title);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-beta.melobit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         retrofitInterface=retrofit.create(RetrofitInterface.class);

         switch (title){
             case "New Song":
                 getNewSongs();
                 break;
             case "Top Singers":
                 getTopSingers();
                 break;

             case "Top 10 Day Song":
                 getTopDays();
                 break;

             case "Top 10 Week Song":
                 getTopWeek();
                 break;

         }
    }
    public void getNewSongs(){
        Call<SongsModel> songsModel=retrofitInterface.getnewSong();
        songsModel.enqueue(new Callback<SongsModel>() {
            @Override
            public void onResponse(Call<SongsModel> call, Response<SongsModel> response) {
                if(response.isSuccessful()){
                    resultSongsList.addAll(response.body().getResults());
                    setAdapter(resultSongsList,null);
                }else {
                    Log.e("stufresE12", response.code()+"" );
                }
            }

            @Override
            public void onFailure(Call<SongsModel> call, Throwable t) {
                Log.e("stufresE1", t.getMessage()+"" );
            }
        });
    }


    public void getTopSingers(){
        Call<ArtistsModel> songsModel=retrofitInterface.getTopSinger();
        songsModel.enqueue(new Callback<ArtistsModel>() {
            @Override
            public void onResponse(Call<ArtistsModel> call, Response<ArtistsModel> response) {
                if(response.isSuccessful()){
                    resultArtistsList.addAll(response.body().getResults());
                    setAdapter(null,resultArtistsList);
                }else {
                    Log.e("stufresE12", response.code()+"" );
                }
            }

            @Override
            public void onFailure(Call<ArtistsModel> call, Throwable t) {
                Log.e("stufresE1", t.getMessage()+"" );
            }
        });
    }
    public void getTopDays(){
        Call<SongsModel> songsModel=retrofitInterface.getTopDay();
        songsModel.enqueue(new Callback<SongsModel>() {
            @Override
            public void onResponse(Call<SongsModel> call, Response<SongsModel> response) {
                if(response.isSuccessful()){
                    resultSongsList.addAll(response.body().getResults());
                    setAdapter(resultSongsList,null);
                }else {
                    Log.e("stufresE12", response.code()+"" );
                }
            }

            @Override
            public void onFailure(Call<SongsModel> call, Throwable t) {
                Log.e("stufresE1", t.getMessage()+"" );
            }
        });
    }
    public void getTopWeek(){
        Call<SongsModel> songsModel=retrofitInterface.getTopWeek();
        songsModel.enqueue(new Callback<SongsModel>() {
            @Override
            public void onResponse(Call<SongsModel> call, Response<SongsModel> response) {
                if(response.isSuccessful()){
                    resultSongsList.addAll(response.body().getResults());
                    setAdapter(resultSongsList,null);
                }else {
                    Log.e("stufresE12", response.code()+"" );
                }
            }

            @Override
            public void onFailure(Call<SongsModel> call, Throwable t) {
                Log.e("stufresE1", t.getMessage()+"" );
            }
        });
    }

    private void setAdapter( List<ResultSongs> resultSongsList1, List<ResultArtists> resultArtistList1) {
        adapterSongs=new AdapterSongs(getApplicationContext(),resultSongsList1,resultArtistList1);
        recyclerView.setAdapter(adapterSongs);

    }
}