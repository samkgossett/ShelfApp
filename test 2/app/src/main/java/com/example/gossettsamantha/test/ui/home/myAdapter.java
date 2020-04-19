package com.example.gossettsamantha.test.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {


    private static final String TAG = "NotesRecyclerAdapter";

    private LayoutInflater layoutInflater;

    private ArrayList<MyListItem> data;
    private Context context;
    private OnNoteListener mOnNoteListener;


    public myAdapter(Context context, ArrayList<MyListItem> data, OnNoteListener onNoteListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.context= context;
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

        MyListItem title = data.get(i);
        viewHolder.textTitle.setText(title.getName());

        MyListItem description = data.get(i);
        viewHolder.textDescription.setText( description.getDesc());

        MyListItem image = data.get(i);
        viewHolder.imageView.setImageResource(image.getImageView() );


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textTitle,textDescription;
        ImageView imageView;
        OnNoteListener onNoteListener;



        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.imageView);

            this.onNoteListener = onNoteListener;

            //Toast.makeText(itemView.getContext(), "view holder " , Toast.LENGTH_SHORT).show();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getAdapterPosition());
            Toast.makeText(view.getContext(), "onClick " , Toast.LENGTH_SHORT).show();
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

