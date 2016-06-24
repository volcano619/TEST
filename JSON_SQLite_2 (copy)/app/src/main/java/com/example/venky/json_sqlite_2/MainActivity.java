package com.example.venky.json_sqlite_2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    EditText editTextId,editrtrname,editctgname,editrtrphone;
    String str="";
    Database database;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete,btnall;

    String url ="http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);



        editTextId = (EditText)findViewById(R.id.editText_Id);
        editrtrname = (EditText)findViewById(R.id.editText_rtrname);
        editctgname = (EditText)findViewById(R.id.editText_ctgname);
        editrtrphone = (EditText)findViewById(R.id.editText_rtrphone);

    btnall=(Button) findViewById(R.id.btnall);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);




        AddData();
        viewAll();
       UpdateData();
        DeleteData();
        Deleteall();

    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
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

                                                //JSONObject jsonObject1=jsonArray.getJSONObject(i);
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
                }
        );
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
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
                }
        );
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = database.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data is not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = database.UpdateData(editTextId.getText().toString(),editrtrname.getText().toString(),editctgname.getText().toString(),editrtrphone.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data is Updated", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this, "Data is not Updated", Toast.LENGTH_LONG).show();
                    }


                }

        );

    }

    public void Deleteall() {
        btnall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.deleteall();



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





}

