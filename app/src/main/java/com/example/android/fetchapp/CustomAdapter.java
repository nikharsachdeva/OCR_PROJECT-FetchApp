package com.example.android.fetchapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    ProgressDialog pd;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView nametxt= (TextView) convertView.findViewById(R.id.nameTxt);
        ImageView img= (ImageView) convertView.findViewById(R.id.movieImage);
       TextView timetxt = (TextView) convertView.findViewById(R.id.timeTxt);

        //BIND DATA
        Spacecraft spacecraft=spacecrafts.get(position);

        nametxt.setText(spacecraft.getName());
        timetxt.setText(spacecraft.getTime());
        final String s = spacecraft.getImageUrl();
        final String n = spacecraft.getName(); // Image ka name utha lia taaki dialog box ka title set kar paayen
        //IMG
        PicassoClient.downloadImage(c,spacecraft.getImageUrl(),img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(c);
                View view1 = inflater.inflate(R.layout.custom , null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                dialog.setView(view1);
                dialog.setTitle(n);
                dialog.setCancelable(true);
                ImageView image = (ImageView) view1.findViewById(R.id.image);
//                image.setImageResource(R.drawable.ic_launcher);
                PicassoClient.downloadImage(c,s,image);
                dialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        nametxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Which Type Of Detail Do You Want ??")
                        .setCancelable(true)
                        .setPositiveButton("VECHILE DETAILs", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                String baseURL = "http://nikhar1610098-eval-test.apigee.net/register/checkindia?RegistrationNumber=";
                                String regNo=n;
                                String extendend = "&username=";
                                String username = "nikharsachdeva13";
                                String myURL = baseURL+regNo+extendend+username;
                                Log.e("URL" , myURL);
                                String result;
                                fetchData getRequest = new fetchData(c);

                                try {
                                    result = getRequest.execute(myURL).get();
                                    if(!(result==null)||(result=="")) {
                                        Example example = new Gson().fromJson(result, Example.class);
                                        Log.e("DATA", new Gson().toJson(example));

                                        CarDetailBottomsheet bottomsheet = new CarDetailBottomsheet();
                                        bottomsheet.setData(example);
                                        bottomsheet.setCancelable(true);
                                        bottomsheet.show(((MainActivity) c).getSupportFragmentManager(), "bottomsheet");
                                    } else {
                                        Toast.makeText(c , "Sorry !! No Detail Found" , Toast.LENGTH_SHORT).show();
                                    }
                                } catch (ExecutionException e) {
                                    Toast.makeText(c , "Some Error Occurred" , Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    Toast.makeText(c , "Unknown Error Occurred" , Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("OWNER DETAILS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Uri uri = Uri.parse("smsto:7738299899");
                                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                                it.putExtra("sms_body", "VAHAN "+n);
                                c.startActivity(it);
                            }
                        })
                        .create().show();
            }
        });


        return convertView;
    }
}