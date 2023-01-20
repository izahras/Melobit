package com.example.melobit;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    RetrofitInterface retrofitInterface;

    RecyclerView recyclerView;
    AdapterSearch adapterSearch;
    List<ResultSearch> searchList;

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView=findViewById(R.id.recysearch);
        searchList=new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-beta.melobit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        editText=findViewById(R.id.editext);
        ((ImageView)findViewById(R.id.img_search)).setOnClickListener(v -> {
            if (!editText.getText().toString().equals(""))
            Search(editText.getText().toString());
        });
    }

    public void Search(String search){
        searchList.clear();
        editText.setText("");
        Call<SongsSearch> songsSearchCall=retrofitInterface.getSearch(search);
        songsSearchCall.enqueue(new Callback<SongsSearch>() {
            @Override
            public void onResponse(Call<SongsSearch> call, Response<SongsSearch> response) {
                if (response.isSuccessful()){
//                    searchList.addAll(response.body().getResults());
//                    setAdapter();
                    for (ResultSearch result : response.body().getResults()) {
                        if (result.getSong()!=null)
                            searchList.add(result);
                    }
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<SongsSearch> call, Throwable t) {
                Log.e("stufError", t.getMessage() );
            }
        });


    }

    private void setAdapter() {
        adapterSearch=new AdapterSearch(searchList,getApplicationContext());
        recyclerView.setAdapter(adapterSearch);
    }
}