package com.example.order_101;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_101.Model.Restaurants;

import java.util.ArrayList;

public class RestaurantAp extends  RecyclerView.Adapter<RestaurantAp.ViewHolder> {
    ArrayList<Restaurants> resList;

    public RestaurantAp(ArrayList<Restaurants> restaurants) {
        this.resList = restaurants;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_res, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(resList.get(position));
        holder.details.setText(resList.get(position).getImage() + "");
    }

    @Override
    public int getItemCount() {
        return resList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.txtName);
        }
    }
}
