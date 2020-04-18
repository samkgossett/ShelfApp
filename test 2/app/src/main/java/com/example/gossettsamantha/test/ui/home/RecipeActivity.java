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

    //ArrayAdapter<String> ingrAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        //Get the recipe from the intent
        int recipeId = (Integer) getIntent().getExtras().get(EXTRA_RECIPEID);

        /*****/
        final ListView ingredientsLv = (ListView) findViewById(R.id.ingredientsListView);

        // Initializing a new String Array
        String[] ingredients = new String[] {
                "Ingredient 1",
                "Ingredient 2",
                "Ingredient 3",
                "Ingredient 4",
                "Ingredient 5",
                "Ingredient 6",
                "Ingredient 7",
                "Ingredient 8",
                "Ingredient 9"
        };

        // Create a List from String Array elements
        final List<String> ingredients_list = new ArrayList<String>(Arrays.asList(ingredients));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ingredients_list);
        ingredientsLv.setAdapter(arrayAdapter);
/***/
        final ListView instructionsLv = (ListView) findViewById(R.id.instructionsListView);

        // Initializing a new String Array
        String[] instructions = new String[] {
                "Instruction 1",
                "Instruction 2",
                "Instruction 3",
                "Instruction 4",
                "Instruction 5",
                "Instruction 6"
        };

        // Create a List from String Array elements
        final List<String> instructions_list = new ArrayList<String>(Arrays.asList(instructions));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, instructions_list);
        instructionsLv.setAdapter(arrayAdapter2);


        //Create a cursor
        SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
        try {
            SQLiteDatabase db = recipeDatabaseHelper.getReadableDatabase();
            //db.setVersion(2);
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "MATCH_PERCENTAGE", "LINK"},
                    "_id = ?",
                    new String[]{Integer.toString(recipeId)},
                    null, null, null);
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the recipe details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                double matchId = cursor.getDouble(3);
                String websiteLink = cursor.getString(4);
                //int ingredients = cursor.getInt(3);

                //Populate the recipe name
                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                //Populate the recipe description
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate the recipe image

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                TextView matchPercentage = (TextView) findViewById(R.id.percentMatch);
                matchPercentage.setText("Match Percentage: " + String.valueOf(matchId));


                //TextView web = (TextView) findViewById(R.id.website);
                //web.setText(websiteLink);

                //Populate the ingredients
                /*
                ListView listIngredients = (ListView) findViewById(R.id.ingredientsListView);
                try {
                    db = recipeDatabaseHelper.getReadableDatabase();
                    cursor = db.query("DRINK",
                            new String[]{"_id", "INGREDIENTS",},
                            null, null, null, null, null);

                    SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                            android.R.layout.simple_list_item_1,
                            cursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1},
                            0);

                    listIngredients.setAdapter(listAdapter);

                } catch(SQLiteException e) {
                */
                //Toast toast = Toast.makeText(this, "Ingredients and Instructions db tables unavailable", Toast.LENGTH_SHORT);
               // toast.show();

            }

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "SQLiteDoneException", Toast.LENGTH_SHORT);
            toast.show();
        }



    }
}

