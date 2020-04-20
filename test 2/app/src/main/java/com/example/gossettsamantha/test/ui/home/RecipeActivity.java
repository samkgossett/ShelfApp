package com.example.gossettsamantha.test.ui.home;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeActivity extends Activity {
    public static final String EXTRA_RECIPEID = "recipeId";
    public static final String EXTRA_RECIPENAME = "name";
    public static final String EXTRA_RECIPEDESC = "desc" ;
    public static final String EXTRA_RECIPEIMAGE = "image";
    public static final String EXTRA_RECIPEMATCH = "match";
    private Cursor cursor;
    private Cursor cursor2;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        //Get the recipe from the intent

        int recipeId = (Integer) getIntent().getExtras().get(EXTRA_RECIPEID);

        String nameId = (String) getIntent().getExtras().get(EXTRA_RECIPENAME);
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(nameId);

        String descId = (String) getIntent().getExtras().get(EXTRA_RECIPEDESC);
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(descId);

        int photoId = (Integer) getIntent().getExtras().get(EXTRA_RECIPEIMAGE);
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(photoId);

        String matchId = (String) getIntent().getExtras().get(EXTRA_RECIPEMATCH);
        TextView matchP = (TextView) findViewById(R.id.percentMatch);
        matchP.setText("Match percentage: " + matchId +"%");

        final ListView ingredientsLv = (ListView) findViewById(R.id.ingredientsListView);

        // Initializing a new String Array
        String[] ingredients = new String[] {};

        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        db = recipeDatabaseHelper.getReadableDatabase();


        final List<String> ingredients_list = new ArrayList<String>(Arrays.asList(ingredients));

        try {

            //db.setVersion(2);

            cursor = db.query("INGREDIENT",
                    new String[]{"ID", "RECIPE_ID", "INGREDIENT"},
                    null,
                    null,
                    null, null, null);

            final String[] ingr = new String[20];

            int ingId = cursor.getColumnIndex("ID");
            int recId = cursor.getColumnIndex("RECIPE_ID");
            int ingredient = cursor.getColumnIndex("INGREDIENT");


            if (cursor != null && cursor.moveToFirst()) {
                //get columns

                int thisId = cursor.getInt(recId);


                //Toast toast = Toast.makeText(this, " " + ingredientsrecipeid + " " + ingredientsid, Toast.LENGTH_SHORT);
                //toast.show();

                int i= 0 ;

                do {

                    if((recipeId == recId) ) {
                        ingr[i]= cursor.getString(ingredient);
                        ingredients_list.add(ingr[i]);
                        i++;
                        //Toast toast = Toast.makeText(this, "" + recId + " " + recipeId + " " + thisId, Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                }
                while (cursor.moveToNext());



/*
                int i = 0;
                do {

                    thisTitle[i] = cursor.getString(titleColumn);
                    thisMatch[i] = cursor.getString(matchColumn);
                    thisPicture[i] = cursor.getInt(pictureColumn);
                    thisDesc[i] = cursor.getString(titleDesc);
                    thisId[i] = cursor.getInt(idColumn);

                    String items = new ;

                    instructions.add(items);
                    i++;
                }
                while (cursor.moveToNext());


 */


                cursor.close();
            }

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "SQLiteException", Toast.LENGTH_SHORT);
            toast.show();
        }

        // Create a List from String Array elements


        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ingredients_list);
        ingredientsLv.setAdapter(arrayAdapter);






        final ListView instructionsLv = (ListView) findViewById(R.id.instructionsListView);

        String[] instructions = new String[] {
                "Instruction 1",
                "Instruction 2",
                "Instruction 3",
                "Instruction 4",
                "Instruction 5",
                "Instruction 6"
        };
        // Initializing a new String Array

        // Create a List from String Array elements
        final List<String> instructions_list = new ArrayList<String>(Arrays.asList(instructions));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, instructions_list);
        instructionsLv.setAdapter(arrayAdapter2);


    }
}

