package com.example.gossettsamantha.test.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.MainActivity;
import com.example.gossettsamantha.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment  {

    public static final String EXTRA_RECIPEID = "recipeId";
    public static final String EXTRA_RECIPENAME = "name";
    public static final String EXTRA_RECIPEDESC = "desc" ;
    public static final String EXTRA_RECIPEIMAGE = "image";
    public static final String EXTRA_RECIPEMATCH = "match";
    private Cursor instrCursor;
    int recipeId;
    private Cursor ingrRecCursor;
    private Cursor ingCursor;
    private Cursor amountCursor;
    private Cursor recipeCursor;

    private double match;


    private HomeViewModel homeViewModel;
    private ArrayList<MyListItem> list;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SQLiteDatabase db;
    private Cursor cursor;
    private  String[] thisMatch;

    private ArrayList<Double> matchList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());

        createRecipeEnteries();
        //createMonstrosity();

        return root;
    }



    private void createRecipeEnteries() {
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
        db = recipeDatabaseHelper.getReadableDatabase();

        //initializing ArrayList of MyListItems to store recipe data from the database
        //this allows the recipe activity page to open to the correct items
        final ArrayList<MyListItem> list = new ArrayList();

        final String[] thisTitle = new String[100];
        thisMatch = new String[100];
        final String[] thisDesc = new String[100];
        final int[] thisPicture = new int[100];
        final int[] thisId = new int[100];


        cursor = db.query("DRINK",
                new String[]{"_id", "ID", "NAME", "MATCH_PERCENTAGE", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                null, null, null, null, "MATCH_PERCENTAGE DESC");

        if (cursor != null && cursor.moveToFirst()) {
            //get columns

            int titleColumn = cursor.getColumnIndex("NAME");
            int titleDesc = cursor.getColumnIndex("DESCRIPTION");
            int matchColumn = cursor.getColumnIndex("MATCH_PERCENTAGE");
            int pictureColumn = cursor.getColumnIndex("IMAGE_RESOURCE_ID");
            int idColumn = cursor.getColumnIndex("ID");



            int i = 0;
            do {

                thisTitle[i] = cursor.getString(titleColumn);
                thisMatch[i] = cursor.getString(matchColumn);
                thisPicture[i] = cursor.getInt(pictureColumn);
                thisDesc[i] = cursor.getString(titleDesc);
                thisId[i] = cursor.getInt(idColumn);

                MyListItem items = new MyListItem(thisTitle[i], "Match percentage: " + thisMatch[i] +"%",  thisPicture[i]);
                //Toast.makeText(getContext(),  " " + cursor.getString(matchColumn) ,Toast.LENGTH_SHORT).show();

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
                //Toast.makeText(getContext(), "Recipe page for this item will pop up." + position + " " + thisId[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), RecipeActivity.class);
                intent.putExtra(RecipeActivity.EXTRA_RECIPEID, thisId[position]);
                intent.putExtra(RecipeActivity.EXTRA_RECIPENAME, thisTitle[position]);
                intent.putExtra(RecipeActivity.EXTRA_RECIPEDESC, thisDesc[position]);
                intent.putExtra(RecipeActivity.EXTRA_RECIPEIMAGE, thisPicture[position]);
                intent.putExtra(RecipeActivity.EXTRA_RECIPEMATCH, thisMatch[position]);

                startActivity(intent);

            }
        });

    }

/*
    private void createMonstrosity() {

        String[] ingredients = new String[] {};

        final List<String> ingredients_list = new ArrayList<String>(Arrays.asList(ingredients));

        Cursor cursor2 = db.query("DRINK",
                new String[]{"_id", "ID"},
                null, null, null, null, null);

        int idColumn = cursor.getColumnIndex("ID");

        ingrRecCursor = db.query("INGREDIENT_RECIPE",
                new String[]{"INGREDIENT_ID", "RECIPE_ID", "AMOUNT_ID"},
                null,
                null,
                null, null, null);

        int ingRecId = ingrRecCursor.getColumnIndex("INGREDIENT_ID");
        int recId = ingrRecCursor.getColumnIndex("RECIPE_ID");
        int inReAmountId = ingrRecCursor.getColumnIndex("AMOUNT_ID");

        ingCursor = db.query("INGREDIENT",
                new String[]{"INGREDIENT_ID", "INGREDIENTNAME", "USER_OWNS"},
                null,
                null,
                null, null, null);

        int userOwns = ingCursor.getColumnIndex("USER_OWNS");
        int ingId = ingCursor.getColumnIndex("INGREDIENT_ID");
        int ingredient = ingCursor.getColumnIndex("INGREDIENTNAME");

        amountCursor = db.query("AMOUNT",
                new String[]{"AMOUNT_ID", "AMOUNT"},
                null,
                null,
                null, null, null);

        int amountId = amountCursor.getColumnIndex("AMOUNT_ID");
        int amount = amountCursor.getColumnIndex("AMOUNT");


        double ingredientTotal = 0;
        double ingredientOwned = 0;
        try {
            if (cursor2 != null && cursor2.moveToFirst()) {
                //get columns


                do {

                    if (ingrRecCursor != null && ingrRecCursor.moveToFirst()) {
                        //get columns

                        do {
                            if(cursor2.getInt(idColumn) == ingrRecCursor.getInt(recId) ) {

                                if (ingCursor != null && ingCursor.moveToFirst()) {


                                    do {

                                        if((ingrRecCursor.getInt(ingRecId) == ingCursor.getInt(ingId)) ) {

                                            if (amountCursor != null && amountCursor.moveToFirst()) {

                                                do {
                                                    int i = 0;

                                                    if ((ingrRecCursor.getInt(inReAmountId) == amountCursor.getInt(amountId))) {


                                                        //ingr[i] =  amountCursor.getString(amount)  + " " + ingCursor.getString(ingredient);

                                                        //ingredients_list.add(ingr[i]);
                                                        i++;

                                                        ingredientTotal++;


                                                    }


                                                }while( amountCursor.moveToNext());
                                            }


                                            if (ingCursor.getInt(userOwns) == 1 ) {

                                                ingredientOwned++;
                                            }

                                            //Toast.makeText(this, "match = " + match + " = " + ingredientOwned + " / " +ingredientTotal  ,Toast.LENGTH_SHORT).show();

                                            match = ingredientOwned/ingredientTotal;
                                            match = match*100;
                                            //Toast.makeText(getContext(), "  " +match + " " + ingrRecCursor.getInt(recId) ,Toast.LENGTH_SHORT).show();

                                            db.execSQL("UPDATE DRINK SET MATCH_PERCENTAGE = " + match + " WHERE ID = "+  ingrRecCursor.getInt(recId)  );



                                        }
                                        //Toast.makeText(getContext(),  " " + ingrRecCursor.getInt(recId) + " " + recipeId ,Toast.LENGTH_SHORT).show();

                                    } while (ingCursor.moveToNext());

                                }

                            }
                        }

                        while (ingrRecCursor.moveToNext());


                        ingrRecCursor.close();


                    }

                    match = ingredientOwned/ingredientTotal;
                    match = match*100;

                    //Toast.makeText(this, "match = " + match + " = " + ingredientOwned + " / " +ingredientTotal  ,Toast.LENGTH_SHORT).show();




                }
                while (cursor.moveToNext());

                cursor.close();

            }
        }catch(SQLiteException e) {
            Toast toast = Toast.makeText(getContext(), "SQLiteException for monstrosity db", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


 */

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}





