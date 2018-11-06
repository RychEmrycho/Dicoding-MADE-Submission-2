package com.developnerz.moviisky.modules.moviedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.developnerz.moviisky.R;
import com.developnerz.moviisky.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    public final static String EXTRA_POSITION = "extra_position";
    private Movie movie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_big_movie_poster) ImageView img_big_movie_poster;

    @BindView(R.id.tv_movie_title) TextView tv_movie_title;

    @BindView(R.id.tv_movie_description) TextView tv_movie_description;

    @BindView(R.id.tv_movie_lang) TextView tv_movie_lang;

    @BindView(R.id.tv_movie_vote) TextView tv_movie_vote;

    @BindView(R.id.tv_movie_adult) TextView tv_movie_adult;

    @BindView(R.id.tv_movie_popularity) TextView tv_movie_popularity;

    @BindView(R.id.tv_movie_release_date) TextView tv_movie_release_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        movie = getIntent().getParcelableExtra(EXTRA_POSITION);

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w780/"+movie.getBackdropPath())
                .placeholder(R.drawable.img_big_light_no_movie_poster)
                //.error(R.mipmap.ic_launcher_round)
                .into(img_big_movie_poster);

        tv_movie_title.setText(movie.getTitle());

        setEmptyField(movie);

        if(movie.getAdult()){
            tv_movie_adult.setText(R.string.yes);
        } else {
            tv_movie_adult.setText(R.string.no);
        }

    }

    public void setEmptyField(Movie movie){
        if(TextUtils.isEmpty(movie.getOverview())){
            setNoData(tv_movie_description);
        } else {
            tv_movie_description.setText(movie.getOverview());
        }

        if(TextUtils.isEmpty(movie.getOriginalLanguage())){
            setNoData(tv_movie_lang);
        } else {
            tv_movie_lang.setText(movie.getOriginalLanguage().toUpperCase());
        }

        if(TextUtils.isEmpty(String.valueOf(movie.getVoteAverage()))){
            setNoData(tv_movie_vote);
        } else {
            tv_movie_vote.setText(String.valueOf(movie.getVoteAverage()));
        }

        if(TextUtils.isEmpty(String.valueOf(movie.getPopularity()))){
            setNoData(tv_movie_popularity);
        } else {
            tv_movie_popularity.setText(String.valueOf(movie.getPopularity()));
        }

        if(TextUtils.isEmpty(movie.getReleaseDate())){
            setNoData(tv_movie_release_date);
        } else {
            tv_movie_release_date.setText(movie.getReleaseDate());
        }
    }

    public void setNoData(TextView view){
        view.setText("-");
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
