package com.example.apoxeo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import java.util.Collections;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    JsonArray data;

    public CustomAdapter() {
        data = new JsonArray();
    }

    public void setData(JsonArray data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.subHeader.setText("Header - " + (position + 1));
        holder.content.setText(data.get(position).getAsJsonObject().get("name").getAsString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView subHeader;
        TextView content;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            subHeader = itemView.findViewById(R.id.subHeader);
            content = itemView.findViewById(R.id.content);

        }
    }
}
