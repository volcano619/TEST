package com.samir;

import java.util.ArrayList;
import java.util.List;

import com.androidadvance.db.DatabaseHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchProduct extends Activity implements TextWatcher {
	EditText _searchbox;
	private ProgressBar showprogress;
	searchtask dotask;
	private ArrayList<ProductModel> _productList;
	ListView _listview;
	DatabaseHelper db;
	public AutoCompleteTextView myAutoComplete;
	private ArrayList<ProductModel> _productList_Temp;
	String query = "";
	ArrayList<ProductModel> modelarray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchproduct);

		db = new DatabaseHelper(getApplicationContext());
		db.getWritableDatabase();
		modelarray=new ArrayList<ProductModel>();
		modelarray.clear();
		modelarray = db.getProudcts();

		Log.e("-----size-----", "-----size-----" + modelarray.size());

		db.close();
		_searchbox = (EditText) findViewById(R.id.txtsearchproduct);
		showprogress = (ProgressBar) findViewById(R.id.showprogress);
		_listview = (ListView) findViewById(R.id.searchlistview);
		_searchbox.addTextChangedListener(textwatcher);

	}

	Runnable runn = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			handlersearch.sendEmptyMessage(0);
		}
	};
	TextWatcher textwatcher = new TextWatcher() {

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			Log.i("---onTextChanged ----", "---------onTextChanged ----");

			if (_searchbox.getText().toString().length() > 1) {
				query = _searchbox.getText().toString().replace(" ", "%20");

				handlersearch.removeCallbacks(runn);
				handlersearch.post(runn);

			} else {
				showprogress.setVisibility(View.GONE);

				if (dotask != null) {
					if (dotask.getStatus().equals(AsyncTask.Status.RUNNING)) {
						dotask.cancel(true);
					}
				}

				handlersearch.removeCallbacks(runn);

				_productList = new ArrayList<ProductModel>();
				_productList.clear();
				_listview.setAdapter(new CustomBaseAdapter(SearchProduct.this,
						_productList));
			}

		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	Handler handlersearch = new Handler() {

		public void handleMessage(android.os.Message msg) {
			dotask = new searchtask();
			dotask.execute();
		};
	};

	private class searchtask extends AsyncTask<Void, Void, Void> {

		protected void onPreExecute() {

			showprogress.setVisibility(View.VISIBLE);
		};

		protected void onPostExecute(Void param) {
			// animation.dismiss();
			showprogress.setVisibility(View.GONE);
			if (_productList == null)
				return;

			ArrayList<String> item = new ArrayList<String>();

			for (int i = 0; i < _productList.size(); i++) {
				item.add(_productList.get(i).productname);
			}

			myAutoComplete = (AutoCompleteTextView) findViewById(R.id.myautocomplete);

			myAutoComplete.addTextChangedListener(SearchProduct.this);

			myAutoComplete.setAdapter(new ArrayAdapter<String>(
					SearchProduct.this,
					android.R.layout.simple_dropdown_item_1line, item));

			_productList_Temp = new ArrayList<ProductModel>();

			for (int i = 0; i < _productList.size(); i++) {

				_productList_Temp.add(_productList.get(i));

			}

			_listview.setAdapter(new CustomBaseAdapter(SearchProduct.this,
					_productList_Temp));

		}

		@Override
		protected Void doInBackground(Void... params) {

			db = new DatabaseHelper(getApplicationContext());
			db.getWritableDatabase();
			ArrayList<ProductModel> product_list = db.getProudcts(query);
			System.out.println("product_list.size>>>>>" + product_list.size());

			for (int i = 0; i < product_list.size(); i++) {

				String tidno = product_list.get(i).getIdno();

				System.out.println("tidno>>>>>" + tidno);
				String tname = product_list.get(i).getProductname();
				String tprice = product_list.get(i).getProductprice();

				ProductModel _ProductModel = new ProductModel();

				_ProductModel.setIdno(tidno);
				_ProductModel.setProductname(tname);
				_ProductModel.setProductprice(tprice);

				_productList.add(_ProductModel);
			}
			// _productList = _parser.getProductList();

			return null;
		}

	}

	private class CustomBaseAdapter extends BaseAdapter {
		LayoutInflater _inflater;

		List<ProductModel> productList;

		public CustomBaseAdapter(Context context, List<ProductModel> productList) {
			_inflater = LayoutInflater.from(context);
			this.productList = productList;

		}

		public int getCount() {
			// TODO Auto-generated method stub
			return productList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder _holder;
			if (convertView == null) {

				convertView = _inflater.inflate(R.layout.product_list, null);
				_holder = new ViewHolder();

				_holder.title = (TextView) convertView
						.findViewById(R.id.txt_title_text);
				_holder.price = (TextView) convertView
						.findViewById(R.id.txt_price);

				convertView.setTag(_holder);
			} else {
				_holder = (ViewHolder) convertView.getTag();
			}

			_holder.title.setText(productList.get(position).productname.trim());

			_holder.price.setText(productList.get(position).productprice);

			return convertView;
		}

		private class ViewHolder {
			TextView title;
			TextView price;
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	//
}
