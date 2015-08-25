package com.swanson.octodroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ListActivity {

    private RepositoryListAdapter mAdapter;

    private GitHub api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new RepositoryListAdapter(this);
        setListAdapter(mAdapter);

        getApi().repositories("swanson", new Callback<List<Repository>>() {

            @Override
            public void success(List<Repository> repositories, Response response) {
                if (repositories.isEmpty()) {
                    displaySadMessage();
                }

                mAdapter.setRepositories(repositories);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                displayErrorMessage();
            }
        });
    }

    public GitHub getApi() {
        if (api == null) {
            api = GitHubService.getService();
        }
        return api;
    }

    public void setApi(GitHub githubApi) {
        api = githubApi;
    }

    private void displaySadMessage() {
        Toast.makeText(MainActivity.this, "No repos :(",
                Toast.LENGTH_LONG).show();
    }

    private void displayErrorMessage() {
        Toast.makeText(MainActivity.this, "Failed to retrieve the user's repos.",
                Toast.LENGTH_LONG).show();
    }
}
