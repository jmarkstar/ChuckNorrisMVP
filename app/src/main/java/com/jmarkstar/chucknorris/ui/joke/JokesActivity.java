package com.jmarkstar.chucknorris.ui.joke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.jmarkstar.chucknorris.R;
import com.jmarkstar.chucknorris.ChuckNorrisApplication;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.presenter.jokes.JokeContract;
import com.jmarkstar.core.presenter.jokes.JokeModule;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesActivity extends AppCompatActivity implements JokeContract.JokeView {

    @Inject JokeContract.JokePresenter mJokePresenter;

    @BindView(R.id.tv_count) TextView mTvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        ButterKnife.bind(this);

        DaggerJokeComponent
            .builder()
            .applicationComponent( ((ChuckNorrisApplication)getApplication()).getApplicationComponent())
            .jokeModule(new JokeModule(this))
            .build()
            .inject(this);

         mJokePresenter.onGetRandomJokes(10);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showUnauthorizedError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessageLayout(boolean show) {

    }

    @Override
    public void showJokes(ArrayList<JokeModel> jokes) {
        Log.v("JokesActivity", "jokes size = "+jokes.size());
        mTvCount.setText("Jokes size = "+jokes.size());
    }
}
