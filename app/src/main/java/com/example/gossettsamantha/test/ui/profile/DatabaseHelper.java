package com.example.gossettsamantha.test.ui.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String dbName= "Dietary Prefernces";
    private static final String tablePrefernces = "Preferences";

    private static final String id ="ID";
    private static final String NAME = "Name";

    private static final String CreateSongTable= "CREATE TABLE " + tablePrefernces + " " +
            "("+id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+ " TEXT )";

    public DatabaseHelper(Context context){
        super(context, dbName, null,   1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CreateSongTable);


    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il){
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTs "+ tableCourse);

        onCreate(sqLiteDatabase);
    }

    public boolean insertPreference(SQLiteDatabase db, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name );

        long result = db.insert(tablePrefernces, null, contentValues);

        return  result != -1;
    }

    public Cursor viewPreferences(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + tablePrefernces;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

    public void deletePreference(String pref){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ tablePrefernces + " WHERE "+
                NAME + " =\"" + pref +"\";");
    }
}
