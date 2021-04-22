package com.sata.katalogfilem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sata.katalogfilem.Model.MovieListModel;
import com.sata.katalogfilem.MovieDetailActivity;
import com.sata.katalogfilem.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.AdapterHolder> {
    private Context context;
    private List<MovieListModel.Search> searches;

    public MovieListAdapter(Context context, List<MovieListModel.Search> searches){
        this.context = context;
        this.searches = searches;
    }

    @NonNull
    @Override
    public MovieListAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_movie_card,parent,false);
        AdapterHolder holder = new AdapterHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.AdapterHolder holder, int position) {
        final MovieListModel.Search search = searches.get(position);

        String title = search.getTitle();
        String poster = search.getPoster();
        String tahun = search.getYear();
        String type = search.getType();
        String imdbID = search.getImdbID();

        holder.tvDeskripsi.setText(tahun +" | " + type );
        holder.tvTitile.setText(title);
        Picasso.get()
                .load(poster)
                .into(holder.imgPoster);

        holder.btnMovieDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, MovieDetailActivity.class);
                intentDetail.putExtra("imdbID", imdbID);
                context.startActivity(intentDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView tvTitile, tvDeskripsi;
        ImageView imgPoster;
        Button btnMovieDetail;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            tvDeskripsi = itemView.findViewById(R.id.tv_subtitile);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitile = itemView.findViewById(R.id.tv_Title);
            btnMovieDetail = itemView.findViewById(R.id.bnt_movie_detail);

        }
    }
}
