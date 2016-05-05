package com.samir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidadvance.db.DatabaseHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private static final String rssFeed = "https://www.dropbox.com/s/rhk01nqlyj5gixl/jsonparsing.txt?dl=1";

	private static final String ARRAY_NAME = "student";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String CITY = "city";
	private static final String GENDER = "Gender";
	private static final String AGE = "age";
	private static final String BIRTH_DATE = "birthdate";

	// List<Item> arrayOfList;

	ArrayList<ProductModel> arrayOfList;
	ListView listView;
	NewsRowAdapter objAdapter;
	DatabaseHelper db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		arrayOfList = new ArrayList<ProductModel>();
		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);

		// arrayOfList = new ArrayList<Item>();

		if (Utils.isNetworkAvailable(MainActivity.this)) {
			new MyTask().execute(rssFeed);
		} else {
			showToast("No Network Connection!!!");
		}

	}

	public void Search(View v) {
		startActivity(new Intent(MainActivity.this, SearchProduct.class));
	}
//	public void Update(View v)
//	{
//		startActivity(new Intent(MainActivity.this,AddUpdateValues.class));
//	}

	// My AsyncTask start...

	class MyTask extends AsyncTask<String, Void, String> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return Utils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == result || result.length() == 0) {
				showToast("No data found from web!!!");
				MainActivity.this.finish();
			} else {

				try {
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(ARRAY_NAME);

					db = new DatabaseHelper(MainActivity.this);
					db.getWritableDatabase();
					db.emptyProduct();

					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject objJson = jsonArray.getJSONObject(i);

						ProductModel prod = new ProductModel();

						prod.productname = objJson.getString(NAME);
						prod.productprice = objJson.getString(CITY);
						//prod.idno= objJson.getString(ID);
						

						prod.setProductname(objJson.getString(NAME));
						prod.setProductprice(objJson.getString(CITY));
						//prod.setIdno(objJson.getString(ID));
						
						Log.e("-----size-----",
								"-----size-----" + objJson.getString(NAME));

						db.addProduct(prod);

						// Item objItem = new Item();
						//
						// objItem.setId(objJson.getInt(ID));
						// objItem.setName(objJson.getString(NAME));
						// objItem.setCity(objJson.getString(CITY));
						// objItem.setGender(objJson.getString(GENDER));
						// objItem.setAge(objJson.getInt(AGE));
						// objItem.setBirthdate(objJson.getString(BIRTH_DATE));

						// arrayOfList.add(prod);

					}
					db.close();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// check data...

				/*
				 * for (int i = 0; i < arrayOfList.size(); i++) { Item item =
				 * arrayOfList.get(i); System.out.println(item.getId());
				 * 
				 * System.out.println(item.getId());
				 * System.out.println(item.getName());
				 * System.out.println(item.getCity());
				 * System.out.println(item.getGender());
				 * System.out.println(item.getAge());
				 * System.out.println(item.getBirthdate()); }
				 */

				// Collections.sort(arrayOfList, new Comparator<Item>() {
				//
				// @Override
				// public int compare(Item lhs, Item rhs) {
				// return (lhs.getAge() - rhs.getAge());
				// }
				// });
				setAdapterToListview();

			}

		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showDeleteDialog(position);
	}

	private void showDeleteDialog(final int position) {
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
				.create();
		alertDialog.setTitle("Delete ??");
		alertDialog.setMessage("Are you sure want to Delete it??");
		// db. removeProduct(prod);
		alertDialog.setButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

//				ProductModel model = new ProductModel();
				String name = arrayOfList.get(position).getProductname();
				
				db = new DatabaseHelper(MainActivity.this);
				db.getWritableDatabase();
				
				
				db.removeProduct(name);
				db.close();
				arrayOfList.remove(position);
				objAdapter.notifyDataSetChanged();

			}
		});
		alertDialog.show();

	}

	public void setAdapterToListview() {

		db = new DatabaseHelper(MainActivity.this);
		db.getWritableDatabase();
		arrayOfList.clear();
		arrayOfList = db.getProudcts();
		Log.e("-----size-----", "-----size-----" + arrayOfList.size());

		objAdapter = new NewsRowAdapter(MainActivity.this, R.layout.row,
				arrayOfList);
		listView.setAdapter(objAdapter);
	}

	public void showToast(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
	}

}