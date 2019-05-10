package com.sapana.androidapps.movieinfo.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.sapana.androidapps.movieinfo.R;
import com.sapana.androidapps.movieinfo.database.MySearch;
import com.sapana.androidapps.movieinfo.movie_detail.MovieActivity;
import com.sapana.androidapps.movieinfo.mysearch.MySearchActivity;

import java.util.List;

    public class SearchRecyclerViewAdapter
            extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

        private List<MySearch> MySearchList;

        MySearchActivity searchActivity;

        public SearchRecyclerViewAdapter(MySearchActivity mainActivity, List<MySearch> items) {
            MySearchList = items;
            this.searchActivity = mainActivity;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.search_layout, viewGroup, false);
            return new ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(final com.sapana.androidapps.movieinfo.adapter.SearchRecyclerViewAdapter.ViewHolder holder, final int position) {

            final MySearch mySearch = MySearchList.get(position);
            final String title = mySearch.getTitle();
            final String year = mySearch.getYear();
            final String plot = mySearch.getPlot();
            final String type = mySearch.getType();

            holder.mTitleView.setText(title);
            holder.mYearView.setText(year);
           holder.mPlotView.setText(plot);
           holder.mTypeView.setText(type);

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   MySearch  movie = new MySearch(title,year,plot,type);
                   Intent intent = new Intent(searchActivity, MovieActivity.class);
                   intent.putExtra("Movie",movie);
                   searchActivity.startActivity(intent);
               }
           });

        }

        @Override
        public int getItemCount() {
            if (MySearchList == null) {
                return 0;
            }
            return MySearchList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder  {
            public final View mView;
            public final TextView mTitleView;
            public final TextView mYearView;
            public final TextView mTypeView;
            public final TextView mPlotView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = (TextView) view.findViewById(R.id.tvTitle);
                mYearView = (TextView) view.findViewById(R.id.tvYear);
                mTypeView = (TextView) view.findViewById(R.id.tvType);
                mPlotView = (TextView) view.findViewById(R.id.tvPlot);
            }

        }


    }


