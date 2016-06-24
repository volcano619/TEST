package com.example.venky.volleydemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by venky on 15/6/16.
 */
public class BackgroundTask {
    Context context;
    ArrayList<Contact> arrayList = new ArrayList<>();
    String url = "http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2";
    String str;
    Database database;
    public BackgroundTask(Context context){
        this.context = context;
    }

    public ArrayList<Contact> getList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count =0;
                            while (count<response.length())
                            {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(count);
                                    Contact contact = new Contact(jsonObject.getString("rtrname"),jsonObject.getString("ctgname"),jsonObject.getString("rtrphoneno"));
                                    arrayList.add(contact);
                                    count++;



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);

    return arrayList;
    }



}

