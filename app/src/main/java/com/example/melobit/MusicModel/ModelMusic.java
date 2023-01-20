package com.example.melobit.MusicModel;

import com.uni.melobit.SongModels.ArtistSong;
import com.uni.melobit.SongModels.AudioModel;

import java.util.List;

public class ModelMusic {
    String id;
    List<ArtistSong> artists;
    AudioModel audio;
    String title;
    String downloadCount;
    String lyrics;
    String releaseDate;
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

    public String getLyrice() {
        return lyrics;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
