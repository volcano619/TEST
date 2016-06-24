package com.example.venky.httpurl1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    Response responseObj;
    CustomAdapter adapter;
    String url = "http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2";
    Gson gson;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.rtlist);
        client = new AsyncHttpClient();
        client.get(MainActivity.this, url, new com.loopj.android.http.AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responsestr, Response.class);
                //System.out.println(responsestr);
                //Type collectionType = new TypeToken<Collection<channelSearchEnum>>(){}.getType();
                //Collection<channelSearchEnum> enums = gson.fromJson(yourJson, collectionType);
                adapter = new CustomAdapter(MainActivity.this, responseObj);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}


  /*  @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String responsestr = new String(responseBody);
        gson= new Gson();
        responseObj = gson.fromJson(responsestr,Response.class);
        adapter= new CustomAdapter(MainActivity.this,responseObj);
        listView.setAdapter(adapter);


    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        // Toast.makeText("Not Accessible");

    }
});*/