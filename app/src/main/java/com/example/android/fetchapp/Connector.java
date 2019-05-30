package com.example.android.fetchapp;

//        Responsible for connection to network.
//        We use HTTPUrlConnection.
//        We are making a HTTP GET request to server

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

        public static HttpURLConnection connect(String urlAddress)
        {
            try {
                URL url=new URL(urlAddress);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                //CON PROP
                con.setRequestMethod("GET");
                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);
                con.setDoInput(true);

                return con;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
}
