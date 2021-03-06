package com.example.lottasofiatuominen.yliopistonruokalat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;
    Spinner spinner, spinner2;
    Yliopisto yliopisto;
    Button buttonArv, buttonSelaa;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yliopisto = Yliopisto.getInstance();
        context = MainActivity.this;
        buttonSelaa = (Button) findViewById(R.id.buttonSelaa);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, yliopisto.getRavintolat());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the restaurant from the spinner which menu you want to see

                Ravintola rav = (Ravintola) spinner.getSelectedItem();
                //System.out.println("Ravintolan id: " + rav.getID());
                int ID = rav.getID();
                makeSpinner(rav,ID);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    public void makeSpinner(Ravintola rav, final int ID){
        // fill the spinner with the chosen restaurant's menu

        //System.out.println(rav.getRuokalista());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, rav.getRuokalista());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        buttonArv = (Button) findViewById(R.id.buttonAr);

        // When the user presses the button to review new activity "Arvostele" starts
        buttonArv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Valittu ruoka: " + spinner2.getSelectedItem());
                String text = spinner2.getSelectedItem().toString();
                Intent myintent = new Intent(v.getContext(), Arvostele.class);

                // We take the food and ID of the restaurant to the activity
                myintent.putExtra("text", text);
                myintent.putExtra("ID", String.valueOf(ID));
                startActivityForResult(myintent, REQUEST_CODE);

            }
        });
    }

    public void reviews(View v) {
        // start activity "SelaaArvosteluja" to see all of the reviews

        Intent myintent = new Intent(v.getContext(), SelaaArvosteluja.class);
        startActivity(myintent);
    }

}
