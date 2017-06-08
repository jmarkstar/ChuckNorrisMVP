package com.jmarkstar.chucknorris.ui.joke;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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

    @BindView(R.id.et_count) EditText mEtCount;
    @BindView(R.id.rv_jokes) RecyclerView mRvJokes;
    @BindView(R.id.pg_loading) ProgressBar mPgLoading;

    private JokeAdapter mJokeAdapter;
    private Integer numberOfJokes; //maximum

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        ButterKnife.bind(this);

        DaggerJokeComponent
            .builder()
            .applicationComponent( ((ChuckNorrisApplication)getApplication()).getApplicationComponent())
            .jokeModule(new JokeModule(this))
            .build()
            .inject(this);

        mEtCount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mJokeAdapter.addList(null);
                    if(numberOfJokes != null){
                        getJokes();
                    }else{
                        mJokePresenter.onGetNumberOfJokes();
                    }
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });

        mRvJokes.setLayoutManager( new LinearLayoutManager(this));
        mRvJokes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mJokeAdapter = new JokeAdapter(this);
        mJokePresenter.onGetRandomJokes(10);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jokes_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_put_name :

                return true;
            case R.id.action_random:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showProgress() {
        mPgLoading.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        mPgLoading.setVisibility(View.GONE);
    }

    @Override public void showErrorMessage(@StringRes int errorMessage) {
        Toast.makeText(this, getString(errorMessage), Toast.LENGTH_SHORT).show();
    }

    @Override public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override public void showJokes(ArrayList<JokeModel> jokes) {
        Log.v("JokesActivity", "jokes size = "+jokes.size());
        mJokeAdapter.addList(jokes);
        mRvJokes.setAdapter(mJokeAdapter);
    }

    @Override public void getNumberOfJokes(Integer numberOfJokes) {
        this.numberOfJokes = numberOfJokes;
        getJokes();
    }

    private void getJokes(){
        Integer count = Integer.parseInt(mEtCount.getText().toString());
        if(count <= numberOfJokes){
            mJokePresenter.onGetRandomJokes(count);
        }else{
            showErrorMessage(String.format(getString(R.string.number_jokes_limit), numberOfJokes));
        }
    }
}
