package com.androidadvance.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.samir.ProductModel;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASENAME = "androidadvancesqlite";
	public static String PRODUCTTABLE = "products";
	public static String colProductId = "id";
	public static String _colProductid = "productidno";
	public static String colProductName = "productname";
	public static String colProductPrice = "productprice";
	private ArrayList<ProductModel> cartList = new ArrayList<ProductModel>();
	Context c;

	public DatabaseHelper(Context context) {
		super(context, DATABASENAME, null, 33);
		c = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL("CREATE TABLE if not exists " + PRODUCTTABLE + "("
		// + colProductId + " INTEGER PRIMARY KEY AUTOINCREMENT , "
		// + "productidno" + "TEXT," + colProductName + " TEXT ,"
		// + colProductPrice + " TEXT)");

		db.execSQL("CREATE TABLE if not exists producttable(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "productidno"
				+ " TEXT ,"
				+ "productname"
				+ " TEXT,"
				+ "productprice" + " TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + PRODUCTTABLE);
		onCreate(db);
	}

	public void addProduct(ProductModel productitem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("productidno", productitem.idno);
		contentValues.put("productname", productitem.productname);
		contentValues.put("productprice", productitem.productprice);
		db.insert("producttable", null, contentValues);
		
		Log.e("-----size-----", "-----size-----" + productitem.productname);
		db.close();

	}

	// update

	public void updateProduct(ProductModel productList) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("productname", productList.productname);

		contentValues.put("productprice", productList.productprice);
		db.update("producttable", contentValues, "productidno="
				+ productList.idno, null);

		db.close();
	}

	public void emptyProduct() {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("delete from producttable");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeProduct(String productname) {
		try {
			// SQLiteDatabase db = this.getWritableDatabase();
			// db.execSQL("delete from producttable where productidno="
			// + productid);
			// db.close();

			String[] args = { productname };
			getWritableDatabase().delete("producttable", "productname=?", args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ProductModel> getProudcts() {

		cartList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from producttable", null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					ProductModel item = new ProductModel();

					item.idno = cursor.getString(cursor
							.getColumnIndex("productidno"));

					item.productname = cursor.getString(cursor
							.getColumnIndex("productname"));

					item.productprice = cursor.getString(cursor
							.getColumnIndex("productprice"));

					cartList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return cartList;
	}

	public ArrayList<ProductModel> getProudcts(String record) {

		cartList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(true, "producttable", new String[] {
				"productidno", "productname", "productprice" }, "productname"
				+ "=?", new String[] { record }, null, null, null, null);

		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					ProductModel item = new ProductModel();

					item.idno = cursor.getString(cursor
							.getColumnIndex("productidno"));

					item.productname = cursor.getString(cursor
							.getColumnIndex("productname"));

					item.productprice = cursor.getString(cursor
							.getColumnIndex("productprice"));

					cartList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return cartList;
	}
}
