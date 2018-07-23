package com.udacity.gradle.builditbigger;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

public class EndpointsAsyncTaskTest extends AndroidTestCase{

    @SuppressWarnings("unchecked")
    public void test(){

        String res= null;
        MainActivityFragment activityFragment = new MainActivityFragment();
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(activityFragment);

        try{
            res= endpointsAsyncTask.get();
            Log.i("EnpointTaskTest", "Retrived non empty string: " + res);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertNotNull(res);

    }

}
