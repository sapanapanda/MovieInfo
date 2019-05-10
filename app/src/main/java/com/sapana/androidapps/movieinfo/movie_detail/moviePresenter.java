package com.sapana.androidapps.movieinfo.movie_detail;

import com.sapana.androidapps.movieinfo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class moviePresenter implements  movieContract.Presenter, movieContract.Model.OnFinishedListener{
   movieContract.View  view;
   movieContract.Model model;



    public moviePresenter(movieContract.View view) {
        this.view = view;
        model = new movieModel();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void requestData(String title, String year, String plot, String type) {
        view.showProgress();
        model.getMovieList(this,title,year,plot,type);
    }

    @Override
    public void onFinished(Movie movieList) {
        view.hideProgress();
        List<Movie> list = new ArrayList<>();
        list.add(movieList);
             view.setDataToRecyclerView(list);
    }

    @Override
    public void onFailure(Throwable t) {
view.onResponseFailure(t);
    }
}
