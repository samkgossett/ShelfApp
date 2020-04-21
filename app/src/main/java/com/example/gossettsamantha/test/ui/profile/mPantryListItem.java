package com.example.gossettsamantha.test.ui.profile;

import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;
import java.util.stream.Stream;

public class mPantryListItem extends ArrayList<String> {
    private String name;
    private String desc;
    private int imageViewDrawable;

    private int i;

    public mPantryListItem() {
        name = "name";
        desc = "desc";
        imageViewDrawable = R.drawable.chickenench;
    }

    public mPantryListItem(String name, String desc, int imageViewDrawable) {
        this.name = name;
        this.desc = desc;
        this.imageViewDrawable= imageViewDrawable;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setDesc(String name){
        this.desc=desc;
    }
    public String getDesc(){
        return desc;
    }
    public void setImageView(String name){
        this.imageViewDrawable=imageViewDrawable;
    }
    public int getImageView(){
        return imageViewDrawable;
    }

    public static mPantryListItem fromCursor(Cursor cursor) {
        //TODO return your mPantryListItem from cursor.
        return mPantryListItem.fromCursor(cursor);
    }

    @NonNull
    @Override
    public Stream<String> stream() {
        return null;
    }
}