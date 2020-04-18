package com.example.gossettsamantha.test.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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

    //private ArrayList<TestItem> mTestItemList;

    public View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            MyListItem thisItem = list.get(position);
            Toast.makeText(getContext(), "You Clicked: " + thisItem.getName(), Toast.LENGTH_SHORT).show();
        }
    };







    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);

        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        //ListView listRecipes = (ListView) root.findViewById(R.id.recipe_list);

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

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new myAdapter(getContext(), list, this);

        // after this line
/*
        myAdapter.setOnItemClick(new OnItemClicked() {
            @Override
            public void onItemClick(int position) {

            }
        });



 */




        //Create the listener


/*

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> recyclerView,
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
        //recyclerView.setOnItemClickListener(itemClickListener);
        //adapter.setOnItemClickListener(itemClickListener);

 */
        //listRecipes.setOnItemClickListener(itemClickListener);




/*
    myAdapter recyclerViewAdapter = new myAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        //
        recyclerViewAdapter.setOnItemClickListener(onItemClickListener);
 */
        //adapter = new myAdapter(getContext(), list);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setAdapter(adapter);
        //

        //adapter.setOnItemClickListener(onItemClickListener);


        //adapter.setOnItemClickListener(onItemClickListener);




        recyclerView.setAdapter(adapter);




        return root;
    }


    /*
    public void onNoteClick(int position) {
        Intent intent = new Intent(getContext(), RecipeActivity.class);
        intent.putExtra("Name", TextTitle.get(position));
        startActivity(intent);
    }

     */




    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onNoteClick(int position) {
        Toast.makeText(getContext(), "Recipe page for this item will pop up.",Toast.LENGTH_SHORT).show();

    }
/*
        Intent intent = new Intent(getContext(),
                RecipeActivity.class);
        intent.putExtra(RecipeActivity.EXTRA_RECIPEID, list.get(position));
        startActivity(intent);
    }


 */
}





