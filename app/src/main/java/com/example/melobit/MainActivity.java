package com.example.melobit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt_newSong,txt_TopSingers,txt_topday,txt_topweek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_newSong=findViewById(R.id.txt_newsong);
        txt_TopSingers=findViewById(R.id.txt_topsingers);
        txt_topday=findViewById(R.id.txt_top_songdays);
        txt_topweek=findViewById(R.id.txt_top_songweek);

        findViewById(R.id.btn_search).setOnClickListener(this::onClick);
        findViewById(R.id.btn_newsong).setOnClickListener(this::onClick);
        findViewById(R.id.btn_topsingers).setOnClickListener(this::onClick);
        findViewById(R.id.btn_top_songdays).setOnClickListener(this::onClick);
        findViewById(R.id.btn_top_songweek).setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                break;

            case R.id.btn_newsong:
                StartActivity(txt_newSong.getText().toString());
                break;

            case R.id.btn_topsingers:
                StartActivity(txt_TopSingers.getText().toString());
                break;

            case R.id.btn_top_songdays:
                StartActivity(txt_topday.getText().toString());
                break;

            case R.id.btn_top_songweek:
                StartActivity(txt_topweek.getText().toString());
                break;
        }
    }

    private void StartActivity(String title) {
        startActivity(new Intent(getApplicationContext(),ListSongsActivity.class).putExtra("title",title));
    }
}