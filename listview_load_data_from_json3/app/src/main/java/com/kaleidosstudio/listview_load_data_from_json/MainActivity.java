package com.kaleidosstudio.listview_load_data_from_json;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview_load_data_from_json.R;
import com.kaleidosstudio.listview_load_data_from_json.Download_data.download_complete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {

	public ListView list;
	public ArrayList<Countries> countries = new ArrayList<Countries>();
	public ListAdapter adapter;
	Button btnAddData;
	Database database;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//btnAddData = (Button) findViewById(R.id.button_add);

		list = (ListView) findViewById(R.id.list);
		adapter = new ListAdapter(this);
		list.setAdapter(adapter);

		Download_data download_data = new Download_data((download_complete) this);
		download_data.download_data_from_link("http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2 ");

	}

	private abstract class AsyncTaskRunner extends AsyncTask<String, String, String> {
		public void get_data(final String data) {

			try {
				//String data = null;
				//JSONObject jsonObject=new JSONObject(ParsingDta);
				JSONArray data_array = new JSONArray(data);
				for (int i = 0; i < data_array.length(); i++) {
					JSONObject obj = new JSONObject(data_array.get(i).toString());
					Countries add = new Countries();
					String rtrname = obj.getString("rtrname").toString();
					String ctgname = obj.getString("ctgname").toString();
					String rtrphoneno = obj.getString("rtrphoneno").toString();

					//str+="\n Retailers"+i+ "\n rtrname:"+rtrname+"\n ctgname:"+ctgname+"\n rtrphoneno:" +rtrphoneno+"\n";

					//textView1.setText(str);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	protected void onPostExecute(String rtrname,String ctgname,String rtrphoneno) {
		// execution of result of Long time consuming operation
		boolean isInserted = database.insertData(rtrname, ctgname, rtrphoneno);
		if (isInserted == true)
			Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
		else
			Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
	}







}





