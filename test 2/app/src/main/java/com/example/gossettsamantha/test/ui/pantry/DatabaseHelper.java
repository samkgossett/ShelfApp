package com.example.gossettsamantha.test.ui.pantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Addding Names to the Database/Tables/Columns
    public static final String INGREDIENTS_DATABASE = "Ingredients";
    public static final String INGREDIENT_TABLE = "Ingredients_table";
    public static final String INGRED_COLUMN_ID = "ID"; // of value type int
    public static final String COLUMN_INGREDIENT_NAME = "Food_Name"; // of value type String

    //Creating Table
    private static final String CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + INGREDIENT_TABLE + " " +
            "("+INGRED_COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_INGREDIENT_NAME+ " TEXT )";

    //Start of Editing the Table
    public DatabaseHelper(@Nullable Context context) {
        super(context, INGREDIENTS_DATABASE, null, 1);

    }

    //on creation of the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INGREDIENTS_TABLE);

    }

    //on upgrade of the table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    //inserting to the table
    public boolean insertIngredient(String ingredient_name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_INGREDIENT_NAME, ingredient_name);

        long result = db.insert(INGREDIENT_TABLE, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //deleting from the table
    public void deleteIngredients(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + INGREDIENT_TABLE + " WHERE " + COLUMN_INGREDIENT_NAME + "= ' " + name + "'");
        db.close();

    }




}