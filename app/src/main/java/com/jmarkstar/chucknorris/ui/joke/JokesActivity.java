package com.jmarkstar.chucknorris.ui.joke;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import butterknife.OnClick;

public class JokesActivity extends AppCompatActivity implements JokeContract.JokeView {

    @Inject JokeContract.JokePresenter mJokePresenter;

    @BindView(R.id.et_count) EditText mEtCount;
    @BindView(R.id.rv_jokes) RecyclerView mRvJokes;

    private JokeAdapter mJokeAdapter;

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

        mRvJokes.setLayoutManager( new LinearLayoutManager(this));

        mJokeAdapter = new JokeAdapter(this);
        mJokePresenter.onGetRandomJokes(10);
    }

    @OnClick(R.id.btn_fetch) public void onFetch(){
        Integer count = Integer.parseInt(mEtCount.getText().toString());
        mJokePresenter.onGetRandomJokes(count);
    }

    @Override public void showProgress() {

    }

    @Override public void hideProgress() {

    }

    @Override public void showErrorMessage(@StringRes int errorMessage) {

    }

    @Override public void showErrorMessage(String errorMessage) {

    }

    @Override public void showJokes(ArrayList<JokeModel> jokes) {
        Log.v("JokesActivity", "jokes size = "+jokes.size());
        mJokeAdapter.addList(jokes);
        mRvJokes.setAdapter(mJokeAdapter);
    }
}
