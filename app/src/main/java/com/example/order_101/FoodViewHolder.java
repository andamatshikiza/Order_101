package com.example.order_101;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nameFood;
    public ImageView picFood;

    private ItemClickListener itemClickListener;



    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        nameFood = (TextView)itemView.findViewById(R.id.txtName);
        picFood= (ImageView)itemView.findViewById(R.id.imgFood);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
