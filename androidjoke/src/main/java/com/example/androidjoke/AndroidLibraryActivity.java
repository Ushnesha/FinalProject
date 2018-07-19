package com.example.androidjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javajoke.JavaJoke;

public class AndroidLibraryActivity extends AppCompatActivity {

    public static final String JOKE_ID= "joke_id";

    JavaJoke joke=new JavaJoke();
    String sr = joke.getJoke();
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abdroid_library);
        tv=(TextView) findViewById(R.id.andLibText);
//        tv.setText(S);

        String jokeRes = null;
        jokeRes = getIntent().getStringExtra(JOKE_ID);
        if(jokeRes != null){
            tv.setText(jokeRes);
        }else{
            tv.setText(sr);
            Toast.makeText(this, "No jokes retrieved", Toast.LENGTH_SHORT).show();
        }


    }
}
