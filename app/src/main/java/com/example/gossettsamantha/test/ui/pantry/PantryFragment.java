package com.example.gossettsamantha.test.ui.pantry;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.MainActivity;
import com.example.gossettsamantha.test.R;
import com.example.gossettsamantha.test.ui.home.HomeFragment;
import com.example.gossettsamantha.test.ui.home.RecipeActivity;
import com.example.gossettsamantha.test.ui.home.RecipeDatabaseHelper;
import com.example.gossettsamantha.test.ui.profile.ProfileFragment;

import java.util.ArrayList;

public class PantryFragment extends Fragment {

    private Pantry pantry;

    private  RadioGroup radioGroup;
    private CheckBox checkBox[];
    ListView ingredientsList;
    Button homeB, foodB;
    int click = 0;
    private Cursor ingCursor;
    private  ArrayAdapter arrayAdapter;
    private SQLiteDatabase db;
    private LinearLayout linLay;
    private int ingredient;
    private int ingId;

    final ArrayList<String> arrayList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantry =
                ViewModelProviders.of(this).get(Pantry.class);
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);

        //Establishing variables

            //starts new Database
            ingredientsList = root.findViewById(R.id.IngredientsList);

            //Variable Buttons for the toolbar and database buttons

            foodB = root.findViewById(R.id.food);


            //takes the user back to the home page


            //Shows toast message that the user is already on the ingredients page
            foodB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateIngredients();
                }
            });


        radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup);
        linLay = (LinearLayout) root.findViewById(R.id.linLay);

        createCheckBox();

        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);

        ingredientsList.setAdapter(arrayAdapter);

            return root;
        }

    private void createCheckBox() {

        arrayList.clear();

        checkBox = new CheckBox[100];

        //Toast.makeText(getContext(), "create check box",Toast.LENGTH_SHORT).show();

        try {
            SQLiteOpenHelper recipeDatabaseHelper = new RecipeDatabaseHelper(getContext());
            db = recipeDatabaseHelper.getReadableDatabase();

            ingCursor = db.query("INGREDIENT",
                    new String[]{"INGREDIENT_ID", "USER_OWNS", "INGREDIENTNAME"},
                    null,
                    null,
                    null, null, "INGREDIENTNAME ASC");

            ingId = ingCursor.getColumnIndex("INGREDIENT_ID");
            ingredient = ingCursor.getColumnIndex("INGREDIENTNAME");
            int userOwns = ingCursor.getColumnIndex("USER_OWNS");

            //Toast.makeText(getContext(), " " + ingCursor.getCount(),Toast.LENGTH_SHORT).show();

            if (ingCursor != null && ingCursor.moveToFirst()) {
                //get columns

                int i = 0;
                do {
                    checkBox[i] = new CheckBox(getContext());

                    if ((ingCursor.getInt(userOwns)) == 1) {

                        checkBox[i].setText(ingCursor.getString(ingredient));
                        checkBox[i].setId(i);
                        arrayList.add((String) checkBox[i].getText());
                        radioGroup.addView(checkBox[i]);
                        //Toast.makeText(getContext(), "if this works ur big brain " , Toast.LENGTH_SHORT).show();
                        checkBox[i].setChecked(true);

                    }
                    else {
                        checkBox[i].setText(ingCursor.getString(ingredient));
                        checkBox[i].setId(i);

                        radioGroup.addView(checkBox[i]);
                    }

                    i++;
                } while (ingCursor.moveToNext());

            }


        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(getContext(), "SQLiteException for Ingred db", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


        public void updateIngredients() {

            arrayList.clear();
            db.execSQL("UPDATE INGREDIENT SET USER_OWNS='0' ");

            if (ingCursor != null && ingCursor.moveToFirst()) {

                int x = 0;
                do {
                        if( ( checkBox[x].isChecked() ) && ( arrayList.contains((String) checkBox[x].getText()) != true)  ) {

                            db.execSQL("UPDATE INGREDIENT SET USER_OWNS='1' WHERE INGREDIENT_ID = "+ ingCursor.getInt(ingId)  );
                            arrayList.add((String) checkBox[x].getText());
                            arrayAdapter.notifyDataSetChanged();

                        }
                    x++;

                } while ( ingCursor.moveToNext());
    }
}}

