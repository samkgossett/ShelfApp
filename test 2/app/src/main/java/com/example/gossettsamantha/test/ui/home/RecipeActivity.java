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
    private Cursor instrCursor;
    private Cursor ingrRecCursor;
    private Cursor ingCursor;
    private Cursor amountCursor;

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
            ingrRecCursor = db.query("INGREDIENT_RECIPE",
                    new String[]{"INGREDIENT_ID", "RECIPE_ID", "AMOUNT_ID"},
                    null,
                    null,
                    null, null, null);

            final String[] ingr = new String[20];

            int ingRecId = ingrRecCursor.getColumnIndex("INGREDIENT_ID");
            int recId = ingrRecCursor.getColumnIndex("RECIPE_ID");
            int inReAmountId = ingrRecCursor.getColumnIndex("AMOUNT_ID");

            ingCursor = db.query("INGREDIENT",
                    new String[]{"INGREDIENT_ID", "INGREDIENT"},
                    null,
                    null,
                    null, null, null);

            int ingId = ingrRecCursor.getColumnIndex("INGREDIENT_ID");
            int ingredient = ingCursor.getColumnIndex("INGREDIENT");

            amountCursor = db.query("AMOUNT",
                    new String[]{"AMOUNT_ID", "AMOUNT"},
                    null,
                    null,
                    null, null, null);

            int amountId = amountCursor.getColumnIndex("AMOUNT_ID");
            int amount = amountCursor.getColumnIndex("AMOUNT");

            if (ingrRecCursor != null && ingrRecCursor.moveToFirst()) {
                //get columns

                do {

                    if((recipeId == ingrRecCursor.getInt(recId)) ) {

                        if (ingCursor != null && ingCursor.moveToFirst()) {

                            do {

                                if((ingrRecCursor.getInt(ingRecId) == ingCursor.getInt(ingId)) ) {

                                    if (amountCursor != null && amountCursor.moveToFirst()) {

                                        do {
                                            int i = 0;

                                            if ((ingrRecCursor.getInt(inReAmountId) == amountCursor.getInt(amountId))) {
                                                ingr[i] = ingCursor.getString(ingredient) + ":" + "\n" + amountCursor.getString(amount);
                                                ingredients_list.add(ingr[i]);
                                                i++;
                                            }

                                        }while( amountCursor.moveToNext());
                                }
                                }
                            } while (ingCursor.moveToNext());
                        }
                    }
                }
                while (ingrRecCursor.moveToNext());
                ingrRecCursor.close();
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

      instrCursor = db.query("INSTRUCTION",
                    new String[]{"ID", "RECIPE_ID", "INSTRUCTION"},
                    null,
                    null,
                    null, null, "ID ASC");

            final String[] instr = new String[20];

            int ingId = instrCursor.getColumnIndex("ID");
            int recId = instrCursor.getColumnIndex("RECIPE_ID");
            int instruction = instrCursor.getColumnIndex("INSTRUCTION");

            if (instrCursor != null && instrCursor.moveToFirst()) {
                //get columns

                int i= 0 ;
                do {
                    if((recipeId == instrCursor.getInt(recId)) ) {
                        instr[i]= instrCursor.getString(instruction);
                        instructions_list.add(instr[i]);
                        i++;
                    }
                }
                while (instrCursor.moveToNext());
                instrCursor.close();
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

