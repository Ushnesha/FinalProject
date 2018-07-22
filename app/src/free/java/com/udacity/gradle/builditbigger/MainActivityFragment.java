package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.example.androidjoke.AndroidLibraryActivity;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    ProgressBar progressBar = null;
    public String loadedJoke = null;
    public boolean flag = false;
    Button joke_btn;
    PublisherInterstitialAd publisherInterstitialAd = null;
    private static final String TAG = "FreeDebug";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        publisherInterstitialAd = new PublisherInterstitialAd(getContext());
        //publisherInterstitialAd.setAdUnitId("ca-app-pub-5049614496228986/9099382594");
        publisherInterstitialAd.setAdUnitId("/6499/example/interstitial");
        publisherInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
                requestNewInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.i(TAG, "onAdFailedToLoad: ad Failed to load. Reloading...");
                requestNewInterstitial();
            }

            @Override
            public void onAdLoaded() {
                Log.i(TAG, "onAdLoaded: interstitial is ready!");
                super.onAdLoaded();
            }
        });

        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        joke_btn = (Button) root.findViewById(R.id.joke_btn);
        joke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (publisherInterstitialAd.isLoaded()) {
                    Log.i(TAG, "onClick: interstitial was ready");
                    publisherInterstitialAd.show();
                } else {
                    Log.i(TAG, "onClick: interstitial was not ready.");
                    progressBar.setVisibility(View.VISIBLE);
                    getJoke();
                }
            }
        });

        progressBar= (ProgressBar) root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()

                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
       mAdView.loadAd(adRequest);
        return root;
    }

    public void getJoke(){
        new EndpointsAsyncTask().execute(this);
    }

    public void launchAndroidLibraryActivity(){
        if(!flag){
            Context context = getActivity();
            Intent intent = new Intent(context, AndroidLibraryActivity.class);
            intent.putExtra(AndroidLibraryActivity.JOKE_ID, loadedJoke);
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("EA27D37DF5448BF42AA5F7A6D4F11A9B")
                .build();

        publisherInterstitialAd.loadAd(adRequest);
    }


}
