package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codinguniverse.jokeui.DisplayJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.repository.AsyncJokeLoader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainActivityFragment#} factory method to
 * create an instance of this fragment.
 */
public class MainActivityFragment extends Fragment implements ProgressBarInterface, ShowJoke {

    private Button tellJoke;
    private ProgressBar mProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);
        // Inflate the layout for this fragment
        AdView mAdView = root.findViewById(R.id.adView);
        tellJoke = root.findViewById(R.id.tell_joke);
        mProgressBar = root.findViewById(R.id.loading_indicator);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        MobileAds.initialize(getContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJoke();
            }
        });

        return root;
    }

    public void startJoke(){
        new AsyncJokeLoader(this, this).execute();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        tellJoke.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        tellJoke.setVisibility(View.VISIBLE);
    }

    @Override
    public void openJokeActivity(String joke) {
        if (joke == null) {
            Toast.makeText(getContext(), "Something wrong happen, Please Try Again", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent uiIntent = new Intent(getContext(), DisplayJokeActivity.class);
        uiIntent.putExtra(DisplayJokeActivity.EXTRA_JOKE, joke);
        startActivity(uiIntent);
    }
}