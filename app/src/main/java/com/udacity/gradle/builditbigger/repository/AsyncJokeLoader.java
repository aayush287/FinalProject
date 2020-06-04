package com.udacity.gradle.builditbigger.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.ProgressBarInterface;
import com.udacity.gradle.builditbigger.ShowJoke;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class AsyncJokeLoader extends AsyncTask<Void, Void, String> {

    private ProgressBarInterface mProgressBar;
    private ShowJoke mShowJoke;
    private MyApi mMyApi = null;

    public AsyncJokeLoader(ProgressBarInterface progressBar, ShowJoke showJoke){
        mProgressBar = progressBar;
        mShowJoke = showJoke;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.showProgressBar();
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(mMyApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            mMyApi = builder.build();
        }

        try {
            return mMyApi.getJokes().execute().getDescription();

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressBar.hideProgressBar();
        mShowJoke.openJokeActivity(s);
    }
}
