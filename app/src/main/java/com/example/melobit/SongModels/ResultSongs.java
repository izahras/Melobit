package com.example.melobit.SongModels;

import java.util.List;

public class ResultSongs {
    String id;
    List<ArtistSong> artists;
    AudioModel audio;
    String title;
    ImageSongs image;

    public String getId() {
        return id;
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

    public ImageSongs getImage() {
        return image;
    }
}
