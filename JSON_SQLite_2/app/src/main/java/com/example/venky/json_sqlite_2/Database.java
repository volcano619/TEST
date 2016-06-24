package com.example.venky.json_sqlite_2;


        import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by venky on 4/10/16.
 */
// package com.example.venky.myapplication;

/**
 * Created by Venky on 4/4/2016.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Retailers.db";
    public static final String TABLE_NAME = "retailers_table";
    //   public static final String COL_1 = "billedret";
    //  public static final String COL_2 = "rtrphoneno";
    public static final String COL_2 = "rtrname";
    public static final String COL_3 = "ctgname";
    public static final String COL_4 = "rtrphoneno";
    //private String rtrphoneno;
    //private String ctgname;
    //private String rtradd1;
    //private String billedret;
    //private String rtrname;



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,rtrname TEXT,ctgname TEXT,rtrphoneno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String rtrname, String ctgname,String rtrphoneno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(COL_2,rtrphoneno);
        contentValues.put(COL_2,rtrname);
        contentValues.put(COL_3, ctgname);
        contentValues.put(COL_4, rtrphoneno);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean UpdateData(String id,String rtrname, String ctgname, String rtrphoneno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1,billedret);
        contentValues.put(COL_2,rtrname);
        contentValues.put(COL_3,ctgname);
        contentValues.put(COL_4, rtrphoneno);
        //contentValues.put(COL_4, rtradd1);
        db.update(TABLE_NAME,contentValues, "ID = ?" ,new String[]{id});
        return true;

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});

    }

    public boolean deleteall () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");

        return false;
    }


}








