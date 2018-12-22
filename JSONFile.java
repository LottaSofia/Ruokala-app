package com.example.lottasofiatuominen.yliopistonruokalat;

import android.content.Context;
import android.provider.DocumentsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JSONFile {
    //Context context;
    public void writeJSON(Context context) throws Exception {

        Yliopisto yliopisto = Yliopisto.getInstance();
        //System.out.println("************************************Kansion sijainti: " + this.getClass().getFilesDir());
        System.out.println("writeJSON");
        Ravintola rav;
        try {
            OutputStreamWriter ops = new OutputStreamWriter((context.openFileOutput("myJson.json",Context.MODE_PRIVATE)));

            for (int i = 0; i < yliopisto.getRavintolat().size(); i++){
                JSONObject jsonObject = new JSONObject();
                rav = (Ravintola) yliopisto.getRavintolat().get(i);
                jsonObject.put("name", rav.getNimi());
                ops.write(jsonObject.toString());
            }

            ops.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJSON(Context context) {
        String tie = "myJson.json", response = null;
        System.out.println("###############################");
        try {
            InputStream ins = context.openFileInput(tie);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            StringBuilder sb = new StringBuilder();
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            response = sb.toString();
            ins.close();

            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        System.out.println("############################");
                        System.out.println(jsonObject.getString("name"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("###############################TYHJÄ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
