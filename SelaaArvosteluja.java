package com.example.lottasofiatuominen.yliopistonruokalat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelaaArvosteluja extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> ruuat = new ArrayList<>();
    ArrayList<String> arvosanat = new ArrayList<>();
    ArrayList<String> arviot = new ArrayList<>();
    ArrayList<String> arvostelijat = new ArrayList<>();
    TextView t1, t2, t3, t4;
    XMLFile xmlFile = new XMLFile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selaa_arvosteluja);

        list = xmlFile.readXML(); // gets the arraylist of all reviews
        int i = 0;

        // this next loop separates the foods, rates, reviews and reviewer's names on different arraylists
        if (list.size() != 0) {
            do {
                System.out.println(i + " " + list.get(i));
                ruuat.add(list.get(i));
                i++;
                arvosanat.add(list.get(i));
                i++;
                arviot.add(list.get(i));
                i++;
                arvostelijat.add(list.get(i));
                i++;

            } while (i < list.size());
        }
        listView = (ListView) findViewById(R.id.listView);

        // makes the list view of all of the reviews
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ruuat.size();
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
            convertView =getLayoutInflater().inflate(R.layout.customlayout,null);
            t1 = (TextView) convertView.findViewById(R.id.tvRuoka);
            t2 = (TextView) convertView.findViewById(R.id.tvArvosana);
            t3 = (TextView) convertView.findViewById(R.id.tvArvostelu);
            t4 = (TextView) convertView.findViewById(R.id.tvArvostelija);

            t1.setText(ruuat.get(position));
            t3.setText(arvosanat.get(position));
            t2.setText(arviot.get(position));
            t4.setText(arvostelijat.get(position));
            return convertView;
        }
    }
}
