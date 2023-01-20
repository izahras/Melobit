package com.example.melobit.SongModels;

import com.example.melobit.ArtistModels.ImageArtist;

public class ArtistSong {
    String id;
    String fullName;
    String sumSongsDownloadsCount;
    ImageArtist image;

    public ImageArtist getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSumSongsDownloadsCount() {
        return sumSongsDownloadsCount;
    }
}
