package com.example.android.fetchapp;

        //Parses our downloaded json data natively.
        //Then passes this data to CustomAdapter to be bound.

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    public DataParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }else {
            //BIND DATA TO LISTVIEW
            CustomAdapter adapter=new CustomAdapter(c,spacecrafts);
            lv.setAdapter(adapter);
        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            spacecrafts.clear();
            Spacecraft spacecraft;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                int id=jo.getInt("id");
                String name=jo.getString("Registration_Plates");
                String imageUrl=jo.getString("Image_Url");
                String time=jo.getString("Time");

                spacecraft=new Spacecraft();

                spacecraft.setId(id);
                spacecraft.setName(name);
                spacecraft.setImageUrl(imageUrl);
                spacecraft.setTime(time);

                spacecrafts.add(spacecraft);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}