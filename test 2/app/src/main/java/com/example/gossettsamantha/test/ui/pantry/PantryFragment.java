package com.example.gossettsamantha.test.ui.pantry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class PantryFragment extends Fragment {

    private Pantry pantry;

    RecyclerView recyclerView;
    ArrayList<String> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantry =
                ViewModelProviders.of(this).get(Pantry.class);
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);

        return root;
    }


}
