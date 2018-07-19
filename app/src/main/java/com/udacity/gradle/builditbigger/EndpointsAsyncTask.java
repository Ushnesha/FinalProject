package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.androidjoke.AndroidLibraryActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static MyApi myApiService = null;
    private MainActivityFragment mainActivityFragment;
    private Context context;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override                         public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
//                    AndroidJsonFactory(), null)
//                    .setRootUrl("https://build-it-bigger-210112.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        mainActivityFragment = params[0];
        context = mainActivityFragment.getActivity();

        try {
            return myApiService.sayHi("Ushnesha").execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
      /*  // Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(context, AndroidLibraryActivity.class);
        // Put the string in the envelope
        intent.putExtra(AndroidLibraryActivity.JOKE_ID,result);
        context.startActivity(intent); */

//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        mainActivityFragment.loadedJoke = result;
        mainActivityFragment.launchAndroidLibraryActivity();

    }
}