package com.sata.katalogfilem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.sata.katalogfilem.Model.MovieDetailModel;
import com.sata.katalogfilem.Model.MovieListModel;
import com.sata.katalogfilem.Retrofit.ApiService;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    String id;
    TextView tvDetailTitle;
    ImageView ivBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvDetailTitle = findViewById(R.id.tv_detail_title);
        ivBanner = findViewById(R.id.expandedImage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout coll_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        coll_toolbar.setTitle("Detail filem");

        Intent intent = getIntent();
        id = intent.getStringExtra("imdbID");

        onLoadDetailData(id);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onLoadDetailData(String id){
        Map<String, String> params = new HashMap<>();
        params.put("apikey","b45dad4f");
        params.put("i", id);

        ApiService.endpoint().geDataDetail(params).enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                tvDetailTitle.setText( response.body().getTitle());
                Picasso.get()
                        .load( response.body().getPoster())
                        .into(ivBanner);
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {

            }
        });

    }
}
