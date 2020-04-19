package com.example.gossettsamantha.test.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements
        myAdapter.OnNoteListener {

    private HomeViewModel homeViewModel;
    private SQLiteDatabase db;
    private Cursor cursor;
    RecyclerView recyclerView;
    private ArrayList<MyListItem> list;
    myAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        db = recipeDatabaseHelper.getReadableDatabase();
        ArrayList<MyListItem> list = new ArrayList();

        String[] thisTitle = new String[20];
        String[] thisMatch = new String[20];
        int[] thisPicture = new int[20];


        cursor = db.query("DRINK",
                new String[]{"_id", "NAME", "MATCH_PERCENTAGE", "IMAGE_RESOURCE_ID"},
                null, null, null, null, "MATCH_PERCENTAGE DESC");

        if (cursor != null && cursor.moveToFirst()) {
            //get columns
            int titleColumn = cursor.getColumnIndex("NAME");
            int matchColumn = cursor.getColumnIndex("MATCH_PERCENTAGE");
            int pictureColumn = cursor.getColumnIndex("IMAGE_RESOURCE_ID");

            int i = 0;
            do {
                thisTitle[i] = cursor.getString(titleColumn);
                thisMatch[i] = cursor.getString(matchColumn);
                thisPicture[i] = cursor.getInt(pictureColumn);

                MyListItem items = new MyListItem(thisTitle[i], "Match percentage: " + thisMatch[i] +"%",  thisPicture[i]);

                list.add(items);
                i++;
            }
            while (cursor.moveToNext()) ;
            cursor.close();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new myAdapter(getContext(), list, this);
        recyclerView.setAdapter(adapter);
        return root;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onNoteClick(int position) {
        Toast.makeText(getContext(), "Recipe page for this item will pop up.",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), RecipeActivity.class);
        intent.putExtra(RecipeActivity.EXTRA_RECIPEID, list.get(position));
        startActivity(intent);
    }



}





