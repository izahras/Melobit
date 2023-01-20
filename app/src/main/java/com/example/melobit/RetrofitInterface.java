package com.example.melobit;

import com.uni.melobit.ArtistModels.ArtistsModel;
import com.uni.melobit.MusicModel.ModelMusic;
import com.uni.melobit.SongModels.SongsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("song/new/0/11")
    Call<SongsModel> getnewSong();

    @GET("artist/trending/0/4")
    Call<ArtistsModel> getTopSinger();


    @GET("song/top/day/0/100")
    Call<SongsModel> getTopDay();


    @GET("song/top/week/0/100")
    Call<SongsModel> getTopWeek();


    @GET("song/{id}")
    Call<ModelMusic> getMusic(@Path("id") String id);


    @GET("search/query/{search}/0/50")
    Call<SongsSearch> getSearch(@Path("search") String search);





}
