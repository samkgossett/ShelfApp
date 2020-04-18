package com.example.gossettsamantha.test.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ImageView IMGS[];
    RecyclerView recyclerView;

    myAdapter adapter;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        ListView listRecipes = (ListView) root.findViewById(R.id.recipe_list);
     /*
        try {
            db = recipeDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getContext(),
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);



            listRecipes.setAdapter(listAdapter);





        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


      */


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

                MyListItem items = new MyListItem(thisTitle[i], thisMatch[i],  thisPicture[i]);

                list.add(items);
                i++;
            }
            while (cursor.moveToNext()) ;
            cursor.close();
        }

        adapter = new myAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        //Create the listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listRecipes,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Pass the recipe the user clicks on to RecipeActivity
                        Intent intent = new Intent(getActivity(),
                                RecipeActivity.class);
                        intent.putExtra(RecipeActivity.EXTRA_RECIPEID, (int) id);
                        startActivity(intent);
                    }
                };

        //Assign the listener to the list view
        listRecipes.setOnItemClickListener(itemClickListener);


        return root;
    }





    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}




