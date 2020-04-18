package com.example.gossettsamantha.test.ui.home;

import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;
import java.util.stream.Stream;

public class MyListItem extends ArrayList<String> {
    private String name;
    private String desc;
    private int imageViewDrawable;

    private int i;

    public MyListItem() {
        name = "name";
        desc = "desc";
        imageViewDrawable = R.drawable.chickenench;
    }

    public MyListItem(String name, String desc, int imageViewDrawable) {
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

    public static MyListItem fromCursor(Cursor cursor) {
        //TODO return your MyListItem from cursor.
        return MyListItem.fromCursor(cursor);
    }

    @NonNull
    @Override
    public Stream<String> stream() {
        return null;
    }
}