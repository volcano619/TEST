package com.example.venky.json_sqlite_2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String ParsingDta= "{\"Retailers\":[{\"billedret\":0,\"class\":\"com.enteleki.sfa.botree.RetailerSummary\",\"ctgcode\":\"G003\",\"ctgname\":\"Cosmetic\",\"distcode\":\"DL/0004\",\"mktid\":6,\"rtradd1\":\"D 12/225 Sec 7\",\"rtrclassid\":12,\"rtrcode\":\"RE0012\",\"rtrid\":5,\"rtrname\":\"Aggarwal Cosmetics\",\"rtrphoneno\":\"27049003\",\"srpcde\":\"2\",\"valueclasscode\":\"CL004\",\"valueclassname\":\"D\"}]}";
    TextView textView1;
    EditText editTextId,editrtrname,editctgname,editrtrphone;
    String str="";
    Database database;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);

        //database.getWritableDatabase();
        editTextId = (EditText)findViewById(R.id.editText_Id);
        editrtrname = (EditText)findViewById(R.id.editText_rtrname);
        editctgname = (EditText)findViewById(R.id.editText_ctgname);
        editrtrphone = (EditText)findViewById(R.id.editText_rtrphone);
        textView1=(TextView)findViewById(R.id.textView1);
        //listView=(ListView)findViewById(R.id.listView);
        btnAddData = (Button)findViewById(R.id.button_add);
       // btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);

     //   Download_data download_data = new Download_data((Download_data.download_complete) this);
      //  download_data.download_data_from_link("http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2");

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
                            JSONObject jsonObject=new JSONObject(ParsingDta);
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

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }





}

