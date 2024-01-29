package com.example.passkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DBNAME="passkeeper";
    private static int DBVERSION=1;

    private static String TABLENAME="pkeeper";
    private static String COLID="id";
    private static String COLENTITI="entiti";
    private static String COLUSERNAME="username";
    private static String COLPASSWORD="password";
    private static String COLDESC="desc";
    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLENAME + "("
                + COLID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLENTITI + " TEXT,"
                + COLUSERNAME + " TEXT,"
                + COLPASSWORD + " TEXT,"
                + COLDESC + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS "+ TABLENAME;

        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<password> getAllEntiti() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        ArrayList<password> entitiList = new ArrayList<>();
        if (rs.moveToFirst()){
            do{
                entitiList.add(new password(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }while (rs.moveToNext());
        }
        rs.close();
        if (entitiList.size()<1){{
            entitiList.add(new password("No Data","Found","in","Database"))
        }

        return  entitiList;

        }
        public long addNewEntiti(String entiti, String username, String password, String desc) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(COLENTITI, entiti);
            cv.put(COLUSERNAME, username);
            cv.put(COLPASSWORD, password);
            cv.put(COLDESC, desc);

            long rValue = db.insert(TABLENAME,null, cv);

            return rValue;


        }
        public long updateNewEntiti(String oldEntiti, String newEntiti, String newUsername, String newPassword, String newDesc) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(COLENTITI, entiti);
            cv.put(COLUSERNAME, username);
            cv.put(COLPASSWORD, password);
            cv.put(COLDESC, desc);

            long rValue = db.update(TABLENAME,"entiti=?", new String[]{oldEntiti});

            return rValue;


        }
        public long deleteNewEntiti(String oldEntiti) {

            SQLiteDatabase db = this.getWritableDatabase();

            long rValue = db.delete(TABLENAME,"entiti=?", new String[]{oldEntiti});

            return rValue;


        }
    }
}
