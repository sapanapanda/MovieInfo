package com.sapana.androidapps.movieinfo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.sapana.androidapps.movieinfo.R;
import com.sapana.androidapps.movieinfo.model.Movie;
import com.sapana.androidapps.movieinfo.movie_detail.MovieActivity;

import java.util.List;

public class MovieRecyclerViewAdapter
        extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Movie> movieList;

    MovieActivity mainActivity;

    public MovieRecyclerViewAdapter(MovieActivity mainActivity, List<Movie> items) {
        movieList = items;
        this.mainActivity = mainActivity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Movie movie = movieList.get(position);
        final String title = movie.getTitle();
        final String imdbId = movie.getImdbID();
        final String director = movie.getDirector();
        final String year = movie.getYear();
        holder.mDirectorView.setText(director);
        holder.mTitleView.setText(title);
        holder.mYearView.setText(year);

        final String imageUrl;
        if (movie.getPoster() != null) {
            if (!movie.getPoster().equals("N/A")) {
                imageUrl = movie.getPoster();
            } else {
                // default image if there is no poster available
                imageUrl = "";
            }
            holder.mThumbImageView.layout(0, 0, 0, 0); // invalidate the width so that glide wont use that dimension
            Glide.with(mainActivity).load(imageUrl).into(holder.mThumbImageView);
        }

    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mYearView;
        public final TextView mDirectorView;
        public final ImageView mThumbImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.movie_title);
            mYearView = view.findViewById(R.id.movie_year);
            mThumbImageView = view.findViewById(R.id.thumbnail);
            mDirectorView = view.findViewById(R.id.movie_director);
        }

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        //  Glide.clear(holder.mThumbImageView);
    }
}
