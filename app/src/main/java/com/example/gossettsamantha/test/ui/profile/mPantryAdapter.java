package com.example.gossettsamantha.test.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class mPantryAdapter extends RecyclerView.Adapter<mPantryAdapter.ViewHolder> {


    private static final String TAG = "NotesRecyclerAdapter";

    private LayoutInflater layoutInflater;

    private ArrayList<mPantryListItem> data;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public mPantryAdapter(Context context, ArrayList<mPantryListItem> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        mPantryListItem title = data.get(i);
        viewHolder.textTitle.setText(title.getName());

        mPantryListItem description = data.get(i);
        viewHolder.textDescription.setText(description.getDesc());

        mPantryListItem image = data.get(i);
        viewHolder.imageView.setImageResource(image.getImageView());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textDescription;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.imageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            listener.onItemClick(getAdapterPosition());

                }

            });
        }

    }
}

