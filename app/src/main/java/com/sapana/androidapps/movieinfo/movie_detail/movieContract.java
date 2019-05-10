package com.sapana.androidapps.movieinfo.movie_detail;

import com.sapana.androidapps.movieinfo.model.Movie;

import java.util.List;

public class movieContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(Movie movie);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener,String title,
                            String year,String plot,String type);


    }

public     interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Movie> movieList);


        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestData(String title,String year,String plot,String type);





    }
}

