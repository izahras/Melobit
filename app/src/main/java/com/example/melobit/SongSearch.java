package com.example.melobit;

import com.example.melobit.MusicModel.AlbumMusic;
import com.example.melobit.SongModels.ArtistSong;
import com.example.melobit.SongModels.AudioModel;

import java.util.List;

public class SongSearch {
    String id;
    AlbumMusic album;
    List<ArtistSong> artists;
    AudioModel audio;
    String title;

    public String getId() {
        return id;
    }

    public AlbumMusic getAlbum() {
        return album;
    }

    public List<ArtistSong> getArtists() {
        return artists;
    }

    public AudioModel getAudio() {
        return audio;
    }

    public String getTitle() {
        return title;
    }
}
