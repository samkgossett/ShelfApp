package com.example.gossettsamantha.test.ui.pantry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.gossettsamantha.test.ui.profile.ProfileFragment;

import java.util.ArrayList;

public class PantryFragment extends Fragment {

    private Pantry pantry;
    DatabaseHelper IngredDatabase;
    ListView ingredientsList;

    private  RadioGroup radioGroup;
    private CheckBox checkBox[];
    private LinearLayout linLay;
    int click = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantry =
                ViewModelProviders.of(this).get(Pantry.class);
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);

        //starts new Database
        IngredDatabase = new DatabaseHelper(getContext());
        ingredientsList = root.findViewById(R.id.IngredientsList);

        radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup);

        linLay = (LinearLayout) root.findViewById(R.id.linLay);
        //Variable Buttons for the toolbar and database buttons


        //Ingredients added to the List-View
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apples, Fuji");
        arrayList.add("Bacon, Maple");
        arrayList.add("Beef, Ground");
        arrayList.add("Cheese, Cheddar");
        arrayList.add("Cheese, Swiss");
        arrayList.add("Dates, Dried");
        arrayList.add("Eggs");
        arrayList.add("Fish, Flounder");
        arrayList.add("Grapes");
        arrayList.add("Hot-dogs");
        arrayList.add("Jelly, Grape");
        arrayList.add("Pickles, Dill");
        arrayList.add("Tomato's, Fresh");


        //Array Adapter creates String array for the List View
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
        ingredientsList.setAdapter(arrayAdapter);
        //Adds the strings to database when item is clicked in list-view
        ingredientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int i, long id) {
                click++;

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        click = 0;
                    }
                };

                //If/else statement shows if items is clicked once or twice
                //one clicked will add item to the database
                if (click == 1){
                    handler.postDelayed(runnable, 400);

                    //if/else statement shows whether the item was added and then actually implements the insert method
                    boolean isInserted = IngredDatabase.insertIngredient(arrayList.get(i).toString());
                    if(isInserted == true)
                        Toast.makeText(getContext(), "Item Inserted Into Pantry", Toast.LENGTH_SHORT).show();

                    else
                        Toast.makeText(getContext(), "Item Already In Pantry", Toast.LENGTH_SHORT).show();


                    //click twice means the item is deleted from the database
                }else if ( click == 2 ) {

                    IngredDatabase.deleteIngredients(arrayList.get(i).toString());
                    Toast.makeText(getContext(), "Item Deleted From Pantry", Toast.LENGTH_SHORT).show();

                }else
                    click = 0;

            }
        });


        createCheckBox();




        return root;
    }

    private void createCheckBox() {



       checkBox = new CheckBox[5];


        //Toast.makeText(getContext(), "create check box",Toast.LENGTH_SHORT).show();

        SharedPreferences myPrefs;
        SharedPreferences.Editor myPrefsPrefsEditor;
        final String MY_SHARED_PREF = "isclicked";


        myPrefs = getContext().getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);

        for(int i=0; i<5; i++) {


            checkBox[i] = new CheckBox(getContext());
            checkBox[i].setText("aaa");
            checkBox[i].setId(i + 100);


            myPrefsPrefsEditor = myPrefs.edit();
            myPrefsPrefsEditor.putBoolean("isclicked", checkBox[i].isChecked());
            myPrefsPrefsEditor.commit();

            radioGroup.addView(checkBox[i]);

            myPrefs.getBoolean("isclicked", checkBox[i].isChecked());
        }
        if (checkBox[0].isChecked()) {

            Toast.makeText(getContext(), "create check box",Toast.LENGTH_SHORT).show();

        }

    }


}
