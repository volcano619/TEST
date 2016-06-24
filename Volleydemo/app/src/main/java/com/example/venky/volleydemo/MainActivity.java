package com.example.venky.volleydemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    Button button1;
    Database database;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete,btnall;
    Button buttonAdd,buttonView;
    String url = "http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2";
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView) ;
        btnall = (Button) findViewById(R.id.btnall);
        database=new Database(this);
        viewAll();
        AddDB();
        Deleteall();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DisplayList.class));


            }
        });
    }

public void viewAll(){
    buttonView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor res =database.getAllData();
            if (res.getCount() == 0) {
                //show message
                showMessage("Error", "Nothing found");
                return;
            }

            StringBuffer Buffer = new StringBuffer();
            while (res.moveToNext()) {
                Buffer.append("Id :"+ res.getString(0)+"\n");
                Buffer.append("rtrname :"+ res.getString(1)+"\n");
                Buffer.append("ctgname :"+ res.getString(2)+"\n");
                Buffer.append("rtrphoneno :"+ res.getString(3)+"\n");
            }

            //Show all data
            showMessage("Data",Buffer.toString());


        }
    });

}

    public void AddDB(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try
                                {
                                    JSONArray jsonArray = new JSONArray(response);

                                    for(int i=0;i < jsonArray.length(); i++)
                                    {
                                        JSONObject jsonObject= jsonArray.getJSONObject(i);


                                        String rtrname=jsonObject.getString("rtrname").toString();
                                        String ctgname=jsonObject.getString("ctgname").toString();
                                        String rtrphoneno=jsonObject.getString("rtrphoneno").toString();

                                        str+= "\n rtrname:"+rtrname+"\n ctgname:"+ctgname+"\n rtrphoneno:" +rtrphoneno+"\n";
                                        boolean isInserted = database.insertData(rtrname,ctgname,rtrphoneno);


                                    }
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                    //textView1.setText(str);
                                }
                                catch(JSONException e)
                                {
                                    e.printStackTrace();
                                }

                                requestQueue.stop();



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        requestQueue.stop();


                    }
                });
                requestQueue.add(stringRequest);


            }
        });

    }

    public void Deleteall() {
        btnall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.deleteall();
                        Toast.makeText(MainActivity.this, "Database has been Cleared", Toast.LENGTH_LONG).show();



                    }


                }

        );

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


/*String rtrname=jsonObject.getString("rtrname");
                                    String ctgname=jsonObject.getString("ctgname");
                                    String rtrphoneno=jsonObject.getString("rtrphoneno");

                                    str+= "\n rtrname:"+rtrname+"\n ctgname:"+ctgname+"\n rtrphoneno:" +rtrphoneno+"\n";
                                    database.insertData(rtrname,ctgname,rtrphoneno);*/



}
