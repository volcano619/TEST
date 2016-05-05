package com.kaleidosstudio.listview_load_data_from_json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.listview_load_data_from_json.R;
import com.kaleidosstudio.listview_load_data_from_json.Download_data.download_complete;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


public class MainActivity extends Activity implements download_complete {

	public ListView list;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public ListAdapter adapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView) findViewById(R.id.list);			
		adapter = new ListAdapter(this);
		list.setAdapter(adapter);
		
		Download_data download_data = new Download_data((download_complete) this);
		download_data.download_data_from_link("http://www.kaleidosblog.com/tutorial/tutorial.json");
		
	}
	


	public void get_data(String data)
	{
		
		try {
			JSONArray data_array=new JSONArray(data);
			
			for (int i = 0 ; i < data_array.length() ; i++)
			{
				JSONObject obj=new JSONObject(data_array.get(i).toString());

				Countries add=new Countries();
				add.name = obj.getString("country");
				add.code = obj.getString("code");
				
				countries.add(add);

			}

			adapter.notifyDataSetChanged();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	

}
