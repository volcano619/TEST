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
		download_data.download_data_from_link("http://182.18.161.240:7070/sfaweb-1.1.015/get/retailers/dl-0004/2");
		
	}
	


	public void get_data(String data)
	{
		
		try {
			JSONArray data_array=new JSONArray(data);
			
			for (int i = 0 ; i < data_array.length() ; i++)
			{
				JSONObject obj=new JSONObject(data_array.get(i).toString());
				String[] nameArray;
				String[] distArray;
				String[] latArray;
				nameArray[i] = (data_array.getJSONObject(i).getString("rtrname"));
				distArray[i] = (data_array.getJSONObject(i).getString("ctgname"));
				latArray[i] = (data_array.getJSONObject(i).getString("rtrphone"));
				//lonArray[i] = (data_array.getJSONObject(i).getString("longitude"));

				/*Countries add=new Countries();
				add.billedret = obj.getString("billedret");
				add.rtrcode = obj.getString("rtrcode");
				add.rtrname = obj.getString("rtrname");
				add.valueclassname = obj.getString("valueclassname");
				add.distcode = obj.getString("distcode");
				add.rtradd1 = obj.getString("rtradd1");
				add.rtrclassid = obj.getString("rtrclassid");
				add.valueclasscode = obj.getString("valueclasscode");
				add.rtrid = obj.getString("rtrid");
				add.rtrphoneno = obj.getString("rtrphoneno");
				add.mktid = obj.getString("mktid");
				add.class1 = obj.getString("class");
				add.ctgname = obj.getString("ctgname");
				add.ctgcode = obj.getString("ctgcode");
				add.srpcde = obj.getString("srpcde");
				
				countries.add(add);
			*/
			}

			//adapter.notifyDataSetChanged();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	

}
