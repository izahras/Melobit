package com.example.melobit;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uni.melobit.MusicModel.ModelMusic;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayMusicActivity extends AppCompatActivity {
    RetrofitInterface retrofitInterface;
    public boolean repeate=false;
    public  MediaPlayer mediaPlayer;
    int i=0;
    String pathmusic,namemusci,namesinger;
    TextView txtTotalduration,txtcurrentTime,txtnameSong,txtnameSinger,txt_matn,txt_count_download,txt_count_releasedate;
    ImageView imgPlay,imgbackplaymusic,imgrepeate,relplaypause,imgSinger,imgprogressbar;
    ProgressBar progressBar;
    SeekBar playerseekbar;
    public Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        initializeView();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-beta.melobit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        Call<ModelMusic> modelMusicCall=retrofitInterface.getMusic(getIntent().getStringExtra("id"));
        modelMusicCall.enqueue(new Callback<ModelMusic>() {
            @Override
            public void onResponse(Call<ModelMusic> call, Response<ModelMusic> response) {
                if (response.isSuccessful()){
                    setData(response);
                }else {
                    Toast.makeText(PlayMusicActivity.this, "Error : "+response.code() , Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ModelMusic> call, Throwable t) {
                Toast.makeText(PlayMusicActivity.this, "Error : "+t.getMessage() , Toast.LENGTH_SHORT).show();
                Log.e("stuferrore", t.getMessage()+"" );
            }
        });

        playerseekbar.setMax(100);
        mediaPlayer=new MediaPlayer();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             PressClick(relplaypause);
                if (i==0){
                    imgPlay.setImageResource(R.drawable.pause_24);
                    mediaPlayer.start();
                    updateseekbar();
                    i++;
                }else {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.play_arrow_24);
                    i=0;
                }

            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
               playerseekbar.setSecondaryProgress(percent);
            }
        });


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                playerseekbar.setProgress(0);
               txtcurrentTime.setText("0:00");
                txtTotalduration.setText("0:00");
                imgPlay.setImageResource(R.drawable.play_arrow_24);
                mediaPlayer.reset();
                praparemediaplayer();
            }
        });


      playerseekbar.setOnTouchListener((v, event) -> {
            SeekBar seekBar=(SeekBar) v;
            int playposation=(mediaPlayer.getDuration() / 100)*seekBar.getProgress();
            mediaPlayer.seekTo(playposation);
            txtcurrentTime.setText(miliseconstoTimer(mediaPlayer.getCurrentPosition()));
            return false;
        });



        imgrepeate.setOnClickListener(view -> {
            PressClick(view).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    if (repeate){
                        imgrepeate.setImageResource(R.drawable.first_white);
                        repeate=false;
                    }else {
                        imgrepeate.setImageResource(R.drawable.of_first24);
                        repeate=true;
                    }
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

        });


        imgbackplaymusic.setOnClickListener(view -> {
            onBackPressed();
        });



    }

    private void setData(Response<ModelMusic> response) {
        pathmusic=response.body().getAudio().getMedium().getUrl();
        namemusci=response.body().getTitle();
        namesinger=response.body().getArtists().get(0).getFullName();
        txtnameSong.setText(namemusci);
        txtnameSinger.setText(namesinger);
        praparemediaplayer();
        imgPlay.setImageResource(R.drawable.pause_24);
        mediaPlayer.start();
        updateseekbar();
        i++;
        imgprogressbar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("image"))
                .into(imgSinger);
        txt_matn.setText(response.body().getLyrice()+"");
        txt_count_download.setText(response.body().getDownloadCount());
        txt_count_releasedate.setText(response.body().getReleaseDate().split("T")[0]);
    }

    private void initializeView() {
        txtcurrentTime=findViewById(R.id.txtcurrentTime);
        txtTotalduration=findViewById(R.id.txtTotalduration);
        txt_matn=findViewById(R.id.txt_matn);
        txt_count_download=findViewById(R.id.txt_count_download);
        txtnameSong=findViewById(R.id.txt_name_song);
        txtnameSinger=findViewById(R.id.txt_name_singer);
        txt_count_releasedate=findViewById(R.id.txt_count_releasedate);
        imgPlay=findViewById(R.id.img_play);
        imgbackplaymusic=findViewById(R.id.imgback);
        imgrepeate=findViewById(R.id.imgrepeate);
        imgSinger=findViewById(R.id.img_singer);
        imgprogressbar=findViewById(R.id.img_progressbar);
        relplaypause=findViewById(R.id.relplaypause);
        playerseekbar=findViewById(R.id.playerseekbar);
        progressBar=findViewById(R.id.progressbar);

        imgprogressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public  void praparemediaplayer(){
        try {
            mediaPlayer.setDataSource(pathmusic);
            mediaPlayer.prepare();
//            mediaPlayer= MediaPlayer.create(requireContext(), Uri.parse(pathmusic));

            txtTotalduration.setText(miliseconstoTimer(mediaPlayer.getDuration()));
            if (repeate){
                imgPlay.setImageResource(R.drawable.pause_24);
                mediaPlayer.start();
                updateseekbar();
                i++;
            }
        } catch (Exception e) {
            Log.e("TAG", e.getMessage() );
        }
    }

    public  Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateseekbar();
            long currentduration=mediaPlayer.getCurrentPosition();
            txtcurrentTime.setText(miliseconstoTimer(currentduration));
            if (txtcurrentTime.getText().toString().equals(txtTotalduration.getText().toString())){
                i=0;
               playerseekbar.setProgress(0);
               txtcurrentTime.setText("0:00");
               txtTotalduration.setText("0:00");
                imgPlay.setImageResource(R.drawable.play_arrow_24);
                mediaPlayer.reset();
                praparemediaplayer();
            }
        }
    };

    public  void updateseekbar(){
        if (mediaPlayer.isPlaying()){
            playerseekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) *100));
            handler.postDelayed(updater,1000);
        }
    }


    public  String miliseconstoTimer(long milisecends){
        String timestring="";
        String secendsString;

        int hours= (int) (milisecends / (1000 * 60 * 60));
        int minuts= (int) (milisecends % (1000 * 60 * 60)) / (1000 * 60);
        int secends= (int) ((milisecends % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0){
            timestring=hours+":";
        }

        if (secends < 10){
            secendsString="0" +secends;
        }else {
            secendsString="" +secends;
        }

        timestring= timestring+minuts+":"+secendsString;

        return timestring;

    }
    public  Animation PressClick(View v) {
        Animation scale = AnimationUtils.loadAnimation(v.getContext(), R.anim.press_click);
        v.clearAnimation();
        v.startAnimation(scale);
        return scale;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(updater);
        mediaPlayer.stop();
        playerseekbar.setProgress(0);
        txtcurrentTime.setText("0:00");
       txtTotalduration.setText("0:00");
        imgPlay.setImageResource(R.drawable.play_arrow_24);

    }
}