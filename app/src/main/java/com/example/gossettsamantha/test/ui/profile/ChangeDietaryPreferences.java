package com.example.gossettsamantha.test.ui.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class ChangeDietaryPreferences extends AppCompatActivity {

    CheckBox chkVegan;
    CheckBox chkVegetarian;
    CheckBox chkNutAllergy;
    CheckBox chkGlutenFree;
    CheckBox chkLactoseIntolerant;

    Button saveData;

    String svegan= "Vegan";
    String sVegetarian = "Vegetarian";
    String sNutAllergy = "Nut Allergy";
    String sGluten =" Gluten Free";
    String slactose = "Lactose Intolerant";

    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_dietary_preferences);

        chkVegan = findViewById(R.id.checkVegan);
        chkVegetarian= findViewById(R.id.checkVegetarian);
        chkNutAllergy=findViewById(R.id.checkNutAllergy);
        chkGlutenFree=findViewById(R.id.checkGluten);
        chkLactoseIntolerant=findViewById(R.id.checkLactoseIntolerant);

        if(chkVegan.isChecked()){
            /**/
            arrayList.add(svegan);
            showToast("Vegan");
        }
        if(chkLactoseIntolerant.isChecked()){
            arrayList.add(slactose);
            showToast("Lactose");
        }
        if(chkGlutenFree.isChecked()){
            arrayList.add(sGluten);
            showToast("gluten");
        }
        if(chkNutAllergy.isChecked()){
            arrayList.add(sNutAllergy);
        }
        if(chkVegetarian.isChecked()){
            arrayList.add(sVegetarian);
        }

        chkGlutenFree.setChecked(true);
        chkNutAllergy.setChecked(true);

        saveData = findViewById(R.id.finishEdit);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Preferences",arrayList);
                setResult(RESULT_OK, returnIntent);
                showToast("Sending Message");
                finish();
            }
        });



    }
    public void showToast(String txt){
        Toast.makeText(ChangeDietaryPreferences.this, txt, Toast.LENGTH_LONG).show();

    }

}
