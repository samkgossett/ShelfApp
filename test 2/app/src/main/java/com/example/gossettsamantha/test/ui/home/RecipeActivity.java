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
    private Cursor cursor1;
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


        /********************************************************************************************************/
                                              /*  INGREDIENTS    */

        final ListView ingredientsLv = (ListView) findViewById(R.id.ingredientsListView);

        // Initializing a new String Array
        String[] ingredients = new String[] {};

        final List<String> ingredients_list = new ArrayList<String>(Arrays.asList(ingredients));

        try {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
            db = recipeDatabaseHelper.getReadableDatabase();
            cursor1 = db.query("INGREDIENT_RECIPE",
                    new String[]{"INGREDIENT_ID", "RECIPE_ID"},
                    null,
                    null,
                    null, null, null);

            final String[] ingr = new String[20];

            int ingRecId = cursor1.getColumnIndex("INGREDIENT_ID");
            int recId = cursor1.getColumnIndex("RECIPE_ID");

            cursor2 = db.query("INGREDIENT",
                    new String[]{"INGREDIENT_ID", "INGREDIENT"},
                    null,
                    null,
                    null, null, null);

            int ingId = cursor1.getColumnIndex("INGREDIENT_ID");
            int ingredient = cursor2.getColumnIndex("INGREDIENT");

            if (cursor1 != null && cursor1.moveToFirst()) {
                //get columns

                do {
                    if((recipeId == cursor1.getInt(recId)) ) {
                        int i= 0 ;
                        if (cursor2 != null && cursor2.moveToFirst()) {

                            do {
                                if((cursor1.getInt(ingRecId) == cursor2.getInt(ingId)) ) {
                                    ingr[i]= cursor2.getString(ingredient);
                                    ingredients_list.add(ingr[i]);
                                    i++;
                                }
                            } while (cursor2.moveToNext());
                        }
                    }
                }
                while (cursor1.moveToNext());
                cursor1.close();
            }

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "SQLiteException for Ingredients db", Toast.LENGTH_SHORT);
            toast.show();
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ingredients_list);
        ingredientsLv.setAdapter(arrayAdapter);


/********************************************************************************************************/
                                            /*  INSTRUCTIONS   */

        final ListView instructionsLv = (ListView) findViewById(R.id.instructionsListView);

        String[] instructions = new String[] {};

        final List<String> instructions_list = new ArrayList<String>(Arrays.asList(instructions));

        try {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(this);
            db = recipeDatabaseHelper.getReadableDatabase();

      cursor = db.query("INSTRUCTION",
                    new String[]{"ID", "RECIPE_ID", "INSTRUCTION"},
                    null,
                    null,
                    null, null, "ID ASC");

            final String[] instr = new String[20];

            int ingId = cursor.getColumnIndex("ID");
            int recId = cursor.getColumnIndex("RECIPE_ID");
            int instruction = cursor.getColumnIndex("INSTRUCTION");

            if (cursor != null && cursor.moveToFirst()) {
                //get columns

                int i= 0 ;
                do {
                    if((recipeId == cursor.getInt(recId)) ) {
                        instr[i]= cursor.getString(instruction);
                        instructions_list.add(instr[i]);
                        i++;
                    }
                }
                while (cursor.moveToNext());
                cursor.close();
            }

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "SQLiteException for Instructions db", Toast.LENGTH_SHORT);
            toast.show();
        }

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, instructions_list);
        instructionsLv.setAdapter(arrayAdapter2);

    }
}

