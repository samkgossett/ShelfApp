package com.example.gossettsamantha.test.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.MainActivity;
import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {


    private static final String TAG = "NotesRecyclerAdapter";

    private LayoutInflater layoutInflater;

    private ArrayList<MyListItem> data;

    private OnNoteListener mOnNoteListener;


    public myAdapter(Context context, ArrayList<MyListItem> data, OnNoteListener onNoteListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.mOnNoteListener = onNoteListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data received

        MyListItem title = data.get(i);
        viewHolder.textTitle.setText(title.getName());

        MyListItem description = data.get(i);
        viewHolder.textDescription.setText( description.getDesc());

        MyListItem image = data.get(i);
        viewHolder.imageVieww.setImageResource(image.getImageView() );


    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textTitle,textDescription;
        ImageView imageVieww;
        OnNoteListener mOnNoteListener;


        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            imageVieww = itemView.findViewById(R.id.imageView);

            mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Details.class);
                    i.putExtra("title",data.get(getAdapterPosition()));
                    v.getContext().startActivity(i);
                }
            });

             */
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
        /*
        Intent i = new Intent(view.getContext(), Activity.class);
                    i.putExtra("title",data.get(getAdapterPosition()));
                    v.getContext().startActivity(i);

         */



    }
}

