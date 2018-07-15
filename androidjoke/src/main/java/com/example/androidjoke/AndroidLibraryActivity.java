package com.example.androidjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.javajoke.JavaJoke;

public class AndroidLibraryActivity extends AppCompatActivity {

    JavaJoke joke=new JavaJoke();
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abdroid_library);
        tv=(TextView) findViewById(R.id.andLibText);
        tv.setText(joke.getJoke());
    }
}
