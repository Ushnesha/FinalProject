package com.udacity.gradle.builditbigger;
import android.support.test.runner.AndroidJUnit4;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest{

    @Test
    public void testDoInBackground() throws InterruptedException {
        MainActivityFragment activityFragment = new MainActivityFragment();
        activityFragment.flag=true;
        new EndpointsAsyncTask().execute(activityFragment);
        Thread.sleep(3000);
        assertTrue("Error: Fetched Joke= " + activityFragment.loadedJoke, activityFragment.loadedJoke != null);

    }

}
