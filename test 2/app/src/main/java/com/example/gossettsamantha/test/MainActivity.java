package com.example.gossettsamantha.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_pantry)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ActionBar actionBar =  getSupportActionBar();
        getSupportActionBar().hide();
    }


/*
    public void onClicked(View view) {
        ImageButton image1 = (ImageButton) findViewById(R.id.imageButton2);
        Toast.makeText(MainActivity.this, "recipe page for item " + image1.getId() + " will pop up." ,Toast.LENGTH_SHORT).show();
    }

    public void onClicked2(View view) {
        ImageButton image2 = (ImageButton) findViewById(R.id.imageButton6);
        Toast.makeText(MainActivity.this, "recipe page for item " + image2.getId() + " will pop up.",Toast.LENGTH_SHORT).show();
    }

    public void onClicked14(View view) {
        ImageButton image3 = (ImageButton) findViewById(R.id.imageButton14);
        Toast.makeText(MainActivity.this, "recipe page for item " + image3.getId() + " will pop up.",Toast.LENGTH_SHORT).show();
    }
*/

    public void recRec(View view) {
        Toast.makeText(MainActivity.this, "recommended recipes page will pop up.",Toast.LENGTH_SHORT).show();
    }


    public void favRec(View view) {
        Toast.makeText(MainActivity.this, "favorite recipes page will pop up.",Toast.LENGTH_SHORT).show();

    }

    public void bookRec(View view) {
        Toast.makeText(MainActivity.this, "bookmarked recipe page will pop up.",Toast.LENGTH_SHORT).show();

    }


    public void yourRec(View view) {
        Toast.makeText(MainActivity.this, "Recipe page for this item will pop up.",Toast.LENGTH_SHORT).show();

    }



}
