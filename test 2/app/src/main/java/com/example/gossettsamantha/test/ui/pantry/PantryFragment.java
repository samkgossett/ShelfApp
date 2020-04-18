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
    Adapter adapter;
    ArrayList<String> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pantry =
                ViewModelProviders.of(this).get(Pantry.class);
        View root = inflater.inflate(R.layout.fragment_pantry, container, false);




                items = new ArrayList<>();
                items.add("First CardView Item");
                items.add("Second CardView Item");
                items.add("Third CardView Item");
                items.add("Fourth CardView Item");
                items.add("Fifth CardView Item");
                items.add("Sixth CardView Item");

                recyclerView = root.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new Adapter(getContext(),items);
                recyclerView.setAdapter(adapter);

        return root;
    }


}
