package com.example.lottasofiatuominen.ht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Arvostelu extends AppCompatActivity {

    TextView textView, textView2;
    RatingBar ratingBar;
    Button submitButton, button;
    float rate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostelu);
        String text = getIntent().getStringExtra("text");
        textView = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);
        System.out.println(text);
        textView.setText(text);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitButton = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.button2);
    }

    public void submitRate(View v) {
        rate = ratingBar.getRating();
        String s = "Annoit annokselle arvosanaksi " + ratingBar.getRating() + " jos haluat voit korjata arvosanan antamalla nyt uuden.";
        textView2.setText(s);
    }

    public void saveRating(View v) {
        String t = String.valueOf(ratingBar.getRating());
        Intent intent = new Intent();
        intent.putExtra("rate",t);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Arvostelu.class);
    }
}
