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

import com.example.gossettsamantha.test.MainActivity;
import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private ArrayList<MyListItem> list;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        db = recipeDatabaseHelper.getReadableDatabase();
        final ArrayList<MyListItem> list = new ArrayList();

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
            while (cursor.moveToNext());

            cursor.close();
        }

        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new myAdapter(getContext(), list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Recipe page for this item will pop up." + position,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), RecipeActivity.class);
                intent.putExtra(RecipeActivity.EXTRA_RECIPEID, position);
                startActivity(intent);

            }
        });



        return root;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }


}





