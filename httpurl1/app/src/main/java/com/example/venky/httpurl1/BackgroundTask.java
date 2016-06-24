package com.example.venky.httpurl1;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by venky on 31/5/16.
 */
public class BackgroundTask extends AsyncTask<Void,Void,Void> {
    String json_url = "http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(json_url);
            HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpurlconnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");


            }
            httpurlconnection.disconnect();
            String json_data = stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_data);

            int count=0;
            while (count<jsonObject.length()){

            count++;



            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


}
