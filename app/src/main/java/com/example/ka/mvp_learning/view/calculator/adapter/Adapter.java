package com.example.ka.mvp_learning.view.calculator.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ka.mvp_learning.R;

import java.util.ArrayList;

/**
 * Created by KA on 12/16/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    ArrayList<String> list = new ArrayList<>();


    public void addOperator(String operator)
    {
        list.add(operator);
        notifyItemInserted(list.size()+1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);

            text= (TextView) itemView.findViewById(R.id.text_calculation);

        }
    }
}
