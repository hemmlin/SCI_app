package com.example.sciprojekti;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "COUNTRIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    public static final String VESI = "vedenkulutus";
    //public final List<String> VEDET= getAllVedet();

    // Database Information
    static final String DB_NAME = "JOURNALDEV_COUNTRIES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT, " + VESI + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /**
     * Getting all labels
     * returns list of labels
     * */
    
    public List<String> getAllVedet(){
        List<String> vedet = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + DB_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                vedet.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return vedet;
    }
    /*
    public ArrayList<DatabaseGet> getAllVesi() {
        ArrayList<DatabaseGet> userModelArrayList = new ArrayList<DatabaseGet>();

        String selectQuery = "SELECT  * FROM " + DB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DatabaseGet databaseGet = new DatabaseGet();
                databaseGet.setId(c.getInt(c.getColumnIndex(_ID)));
                databaseGet.setName(c.getString(c.getColumnIndex(SUBJECT)));

                //getting user vesi where id = id from user_hobby table
                String selectHobbyQuery = "SELECT  * FROM " + TABLE_USER_HOBBY +" WHERE "+KEY_ID+" = "+ userModel.getId();
                Log.d("oppp",selectHobbyQuery);
                //SQLiteDatabase dbhobby = this.getReadableDatabase();
                Cursor cHobby = db.rawQuery(selectHobbyQuery, null);

                if (cHobby.moveToFirst()) {
                    do {
                        userModel.setHobby(cHobby.getString(cHobby.getColumnIndex(KEY_HOBBY)));
                    } while (cHobby.moveToNext());
                }

                //getting user city where id = id from user_city table
                String selectCityQuery = "SELECT  * FROM " + TABLE_USER_CITY+" WHERE "+KEY_ID+" = "+ userModel.getId();;
                //SQLiteDatabase dbCity = this.getReadableDatabase();
                Cursor cCity = db.rawQuery(selectCityQuery, null);

                if (cCity.moveToFirst()) {
                    do {
                        userModel.setCity(cCity.getString(cCity.getColumnIndex(KEY_CITY)));
                    } while (cCity.moveToNext());
                }

                // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

     */

}

