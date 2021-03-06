package com.example.lottasofiatuominen.yliopistonruokalat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Arvostele extends AppCompatActivity {
    // In this activity the user can review foods

    TextView textView, textView2;
    RatingBar ratingBar;
    Button submitButton, button;
    EditText editText, editText2;
    float rate = 0;
    Yliopisto yliopisto = Yliopisto.getInstance();
    Ravintola rav;
    Ruoka ruoka, RRuoka;
    XMLFile xmlFile = new XMLFile();
    Context context = Arvostele.this;
    String review;
    String reviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostele);
        String text = getIntent().getStringExtra("text");
        String ID = getIntent().getStringExtra("ID");
        System.out.println(ID);
        textView = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText2);
        editText2 = (EditText) findViewById(R.id.etNimi);
        RRuoka = mikaRavintola(text,ID);


        System.out.println(text);
        textView.setText(text);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submitButton = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.button2);

    }

    public void submitRate(View v) {
        // when user presses the button he's given the option to edit before saving

        final String review = editText.getText().toString();
        System.out.println(review);
        rate = ratingBar.getRating();
        final String reviewer = editText2.getText().toString();

        String s = "Annoit annokselle arvosanaksi " + ratingBar.getRating() + " jos haluat voit korjata arvosanan antamalla nyt uuden.";
        textView2.setText(s);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the user presses the button to save and go back, the review is saved here

                if (review.equals("") == true || ratingBar.getRating() == 0 || reviewer.equals("") == true) {
                    textView2.setText("Kaikkiin ruutuihin tulee vastata, jotta voit tallenaa!");

                }else {
                    RRuoka.UusiArvostelu(review, ratingBar.getRating(), reviewer);

                    // update the xml file
                    xmlFile.writeXML(context);
                    Intent intent = new Intent();

                    // back to the main activity
                    finish();
                }
            }
        });

    }


    /*public static Intent makeIntent(Context context) {
        return new Intent(context, Arvostelu.class);
    }*/

    public Ruoka mikaRavintola(String t, String ID){
        //finds put witch restaurant and food the user is going to rate

        for (int i = 0; i < yliopisto.getRavintolat().size(); i++) {
            rav = yliopisto.getRavintolat().get(i);
            if (String.valueOf(rav.getID()).equals(ID) == true) {
                System.out.println("Löyty" + rav.getNimi());
                for (int j = 0; j < rav.getRuokalista().size(); j++) {
                    ruoka = rav.getRuokalista().get(j);
                    if (ruoka.getRuoka().equals(t) == true) {
                        return ruoka;
                    }
                }
            } else {
                System.out.println(rav.getNimi());
            }
        }
        return null;
    }

}
