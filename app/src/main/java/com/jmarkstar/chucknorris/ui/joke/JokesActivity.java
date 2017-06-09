package com.jmarkstar.chucknorris.ui.joke;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    @BindView(R.id.rv_jokes) RecyclerView mRvJokes;
    @BindView(R.id.pg_loading) ProgressBar mPgLoading;
    @BindView(R.id.tv_count) TextView mTvCount;

    private SearchView mSvSearch;
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

        mRvJokes.setLayoutManager( new LinearLayoutManager(this));
        mRvJokes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mJokeAdapter = new JokeAdapter(this);
        mJokePresenter.onGetRandomJokes(10);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jokes_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSvSearch = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSvSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                Integer count = Integer.parseInt(query);
                callSearch(count);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }

            private void callSearch(Integer count){
                    if(numberOfJokes != null){
                        getJokes(count);
                    }else{
                        mJokePresenter.onGetNumberOfJokes();
                    }
            }
        });
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
        Integer count  = Integer.parseInt(mSvSearch.getQuery().toString());
        getJokes(count);
    }

    private void getJokes(Integer count){
        if(count <= numberOfJokes){
            mJokeAdapter.addList(null);
            mJokePresenter.onGetRandomJokes(count);
        }else{
            showErrorMessage(String.format(getString(R.string.number_jokes_limit), numberOfJokes));
        }
    }
}
