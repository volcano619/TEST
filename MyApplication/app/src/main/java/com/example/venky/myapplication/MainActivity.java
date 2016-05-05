package com.example.venky.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

       Download_data download_data = new Download_data((Download_data.download_complete) this);
        download_data.download_data_from_link("http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2");


        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_Marks);
        editTextId = (EditText)findViewById(R.id.editText_Id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try
                        {
                            JSONObject jsonObject=new JSONObject();
                            JSONArray jsonArray= jsonObject.getJSONArray("Retailers");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String rtrname=jsonObject1.getString("rtrname").toString();
                                String ctgname=jsonObject1.getString("ctgname").toString();
                                String rtrphoneno=jsonObject1.getString("rtrphoneno").toString();

                                str+="\n Retailers"+i+ "\n rtrname:"+rtrname+"\n ctgname:"+ctgname+"\n rtrphoneno:" +rtrphoneno+"\n";
                                boolean isInserted = database.insertData(rtrname,ctgname,rtrphoneno);
                                if (isInserted == true)
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                //textView1.setText(str);
                            }
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
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


    public void AddDataJSON() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(edit)


                    }
                }


        );



    }



    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =myDb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer Buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            Buffer.append("Id :"+ res.getString(0)+"\n");
                            Buffer.append("Name :"+ res.getString(1)+"\n");
                            Buffer.append("Surname :"+ res.getString(2)+"\n");
                            Buffer.append("Marks :"+ res.getString(3)+"\n");
                        }

                        //Show all data
                        showMessage("Data",Buffer.toString());
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

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.UpdateData(editTextId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data is Updated", Toast.LENGTH_LONG).show();

                            else
                            Toast.makeText(MainActivity.this, "Data is not Updated", Toast.LENGTH_LONG).show();
                        }


                }

        );

                }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data is not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }
    /*@Override
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
    }*/
}

