package com.example.gossettsamantha.test.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;
import com.example.gossettsamantha.test.ui.home.RecipeActivity;
import com.example.gossettsamantha.test.ui.home.RecipeDatabaseHelper;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    Button editButton;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>() ;
    ListAdapter tempAdapter;

    public static final int messageRequest = 01;


    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        editButton = root.findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangeDietaryPreferences.class);
                startActivityForResult(intent,1);
            }
        });

        listView = root.findViewById(R.id.listDietaryPreferences);


        arrayList.add("Nut Allergy");
        arrayList.add("Gluten Free");

        tempAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(tempAdapter);


        return root;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                showToast("Message Recieved");
                arrayList= (ArrayList<String>) data.getSerializableExtra("Preferences");

            }
        }
    }

    public void showToast(String txt){
        Toast.makeText(getContext(), txt, Toast.LENGTH_LONG).show();

    }





}

