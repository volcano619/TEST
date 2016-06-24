package com.emildesign.jsonparsingapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private List<Student> studentsList = new ArrayList<Student>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d(TAG, "Getting xml from raw file");
		InputStream jsonFileInputStream = getResources().openRawResource(R.raw.json); // getting json file
		
		String json = readTextFile(jsonFileInputStream);
		Log.d(TAG, "json String: " + json);
		
		studentsList = JSONParsingClass.NativeJsonParseMethodForStudents(json);
		TextView textView = (TextView) findViewById(R.id.main_text_view);
		textView.setText(studentsList.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	 public String readTextFile(InputStream inputStream) {
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		    byte buf[] = new byte[1024];
		    int len;
		    try {
		        while ((len = inputStream.read(buf)) != -1) {
		            outputStream.write(buf, 0, len);
		        }
		        outputStream.close();
		        inputStream.close();
		    } catch (IOException e) {

		    }
		    return outputStream.toString();
		}

}
