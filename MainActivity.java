package com.example.lottasofiatuominen.ht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    Context context = null;
    Spinner spinner;
    ArrayList<Ravintola> ravintolat = new ArrayList<>();
    Ravintola new_ravintola;
    ListView listView;
    ArrayList<String> ruoat = new ArrayList<>();

    String R1 = "Makkara\nJauhelihakeitto";
    String R2 = "Kanakastike\nKasvislasagne";
    String R3 = "Juuresratatouille\nKinkku salaatti";
    String R4 = "Uunilohi\nKasviskeitto";
    int Rn = 1;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRavintolat();
        context = MainActivity.this;
        final ListView listView = (ListView) findViewById(R.id.listView);
        writeFile(R1);
        writeFile(R2);
        writeFile(R3);
        writeFile(R4);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ravintolat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        System.out.println("Kansion sijainti: " + context.getFilesDir());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruoat.clear();
                Ravintola rav = (Ravintola) spinner.getSelectedItem();
                System.out.println(rav.getID());
                int ID = rav.getID();
                if (ID != 0) {
                    openFile(ID);
                    CustomAdapter customAdapter = new CustomAdapter();
                    listView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                text = ruoat.get(position);
                Intent myintent = new Intent(view.getContext(),Arvostelu.class);
                myintent.putExtra("text",text);
                System.out.println(position);
                startActivityForResult(myintent,REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String rate = data.getStringExtra("rate");
                    System.out.println(rate);
                } else {
                    System.out.println("Ei paluu arvoa.");
                }

        }
    }

    public void setRavintolat() {
        new_ravintola = new Ravintola("Valitse ravintola",0);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("LUT Buffet",1);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("Laseri",2);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("AMK",3);
        ravintolat.add(new_ravintola);
        new_ravintola = new Ravintola("Ylioppilastalo",4);
        ravintolat.add(new_ravintola);

    }

    public void openFile(int ID) {
        System.out.println("openFile");
        try {
            String tie = ID + ".txt";
            System.out.println(tie);
            InputStream ins = context.openFileInput(tie);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s = br.readLine()) != null) {
                System.out.println(s);
                ruoat.add(s);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOExpection", "Virhe syötteessä");
        }  finally {
            System.out.println("Luettu");
        }
    }

    public void writeFile (String input) {
        try {
            String tie = Rn + ".txt";
            System.out.println(tie);
            Rn++;
            OutputStreamWriter ops = new OutputStreamWriter(context.openFileOutput(tie,Context.MODE_PRIVATE));
            ops.write(input);
            ops.close();
        } catch (IOException e) {
            Log.e("IOExpection", "Virhe syötteessä");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ruoat.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);
            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(ruoat.get(position));
            return convertView;
        }
    }
}
