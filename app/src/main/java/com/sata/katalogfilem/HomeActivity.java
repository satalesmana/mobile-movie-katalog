package com.sata.katalogfilem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sata.katalogfilem.Adapter.MovieListAdapter;
import com.sata.katalogfilem.Model.MovieListModel;
import com.sata.katalogfilem.Retrofit.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieListAdapter movieListAdapter;
    LinearLayoutManager linearLayoutManager;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        etSearch = findViewById(R.id.et_search_box);

        recyclerView = findViewById(R.id.recle_movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        onLoadMovieData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()== KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    onLoadMovieData();
                }
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_add_new_movie:
                Intent intent = new Intent(this, MovieFormActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_log_out:
                Intent intentLogout = new Intent(this, MovieFormActivity.class);
                startActivity(intentLogout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onLoadMovieData(){
        String keyword = (etSearch.getText().toString().length()>0) ?  etSearch.getText().toString() : "ironman";
        Map<String, String> params = new HashMap<>();
        params.put("apikey","b45dad4f");
        params.put("s", keyword);

        ApiService.endpoint().getData(params).enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                List<MovieListModel.Search> searches = response.body().getSearch();


                movieListAdapter = new MovieListAdapter(HomeActivity.this,searches );
                recyclerView.setAdapter(movieListAdapter);


            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                Log.d("TES GAGAL", t.toString());
            }
        });
    }
}
