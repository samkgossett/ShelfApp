package com.example.gossettsamantha.test.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gossettsamantha.test.R;
import com.example.gossettsamantha.test.ui.pantry.PantryFragment;

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
    int recipeId;
    private Cursor ingrRecCursor;
    private Cursor ingCursor;
    private Cursor amountCursor;
    private ArrayList<Double> thisMatch[];
    private Cursor recipeCursor;
    private  double match;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        //Get the recipe from the intent

        recipeId = (Integer) getIntent().getExtras().get(EXTRA_RECIPEID);

        String nameId = (String) getIntent().getExtras().get(EXTRA_RECIPENAME);
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(nameId);

        String descId = (String) getIntent().getExtras().get(EXTRA_RECIPEDESC);
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(descId);

        int photoId = (Integer) getIntent().getExtras().get(EXTRA_RECIPEIMAGE);
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(photoId);

        thisMatch = new ArrayList[100];

        createIngredientsList();
        createInstructionsList();


    }

public void onBackPressed() {
    super.onBackPressed();

    Intent intent = new Intent(this, HomeFragment.class);
    intent.putExtra(RecipeActivity.EXTRA_RECIPEMATCH, thisMatch);
    startActivity(intent);
    Toast.makeText(this, "backpressed" ,Toast.LENGTH_SHORT).show();

    finish();

}

    private void createIngredientsList() {

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

            if (ingrRecCursor != null && ingrRecCursor.moveToFirst()) {
                //get columns

                do {
                    if(((Integer) getIntent().getExtras().get(EXTRA_RECIPEID) == ingrRecCursor.getInt(recId)) ) {

                        if (ingCursor != null && ingCursor.moveToFirst()) {


                            do {

                                if((ingrRecCursor.getInt(ingRecId) == ingCursor.getInt(ingId)) ) {

                                    if (amountCursor != null && amountCursor.moveToFirst()) {

                                        do {
                                            int i = 0;

                                            if ((ingrRecCursor.getInt(inReAmountId) == amountCursor.getInt(amountId))) {


                                                ingr[i] =  amountCursor.getString(amount)  + " " + ingCursor.getString(ingredient);

                                                ingredients_list.add(ingr[i]);
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
                                    db.execSQL("UPDATE DRINK SET MATCH_PERCENTAGE = " + match + " WHERE ID = "+  ingrRecCursor.getInt(recId)  );



                                }
                                //Toast.makeText(this,  " " + ingrRecCursor.getInt(recId) + " " + recipeId ,Toast.LENGTH_SHORT).show();

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
            TextView matchP = (TextView) findViewById(R.id.percentMatch);
            matchP.setText("Match percentage: " + match);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "SQLiteException for Ingredients db", Toast.LENGTH_SHORT);
            toast.show();
        }

        //String matchId = (String) getIntent().getExtras().get(EXTRA_RECIPEMATCH);






        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ingredients_list);
        ingredientsLv.setAdapter(arrayAdapter);


        Button ingButton = (Button) findViewById(R.id.ingredientsHeader);
        //set onclicklistener for your button
        ingButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "button clicked",Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent( v.getContext(), PantryFragment.class);
                        //intent.putExtra(RecipeActivity.EXTRA_RECIPEID, position);
                        //intent.putExtra(RecipeActivity.NAME, list.get(position).getName());
                        //getApplicationContext().startActivity(new Intent( v.getContext(), PantryFragment.class));
                    }
                });


        ingredientsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Toast.makeText(getApplicationContext(), "ing button clicked!",Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void createInstructionsList() {
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
                    if(((Integer) getIntent().getExtras().get(EXTRA_RECIPEID) == instrCursor.getInt(recId)) ) {
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


        instructionsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Toast.makeText(getApplicationContext(), "instr clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        Button instr = (Button) findViewById(R.id.instructionsHeader);
        //set onclicklistener for your button
        instr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Instr button clicked",Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent( v.getContext(), PantryFragment.class);
                        //intent.putExtra(RecipeActivity.EXTRA_RECIPEID, position);
                        //intent.putExtra(RecipeActivity.NAME, list.get(position).getName());
                        //getApplicationContext().startActivity(new Intent( v.getContext(), PantryFragment.class));
                    }
                });

    }

}

