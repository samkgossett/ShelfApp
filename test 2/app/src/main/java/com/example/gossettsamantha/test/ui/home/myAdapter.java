package com.example.gossettsamantha.test.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gossettsamantha.test.R;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;
    private ArrayList<MyListItem> data;
    private View.OnClickListener mOnItemClickListener;

    public myAdapter(Context context, ArrayList<MyListItem> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle,textDescription;
        ImageView imageVieww;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), RecipeActivity.class);
                    i.putExtra("title",data.get(getAdapterPosition()));
                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDesc);
            imageVieww = itemView.findViewById(R.id.imageView);

        }
    }
}

