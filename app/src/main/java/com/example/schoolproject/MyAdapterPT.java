package com.example.schoolproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.PTModel;

import java.util.List;

public class MyAdapterPT extends RecyclerView.Adapter<MyAdapterPT.ViewHolder>


{
    private List<PTModel> ptList;
    private Context ctx;

    public MyAdapterPT(List<PTModel> ptList, Context ctx) {
        this.ptList = ptList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ptmitemlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        PTModel modeltemp=ptList.get(position);
        holder.vhclass.setText(modeltemp.getStd());
        holder.vhduration.setText(modeltemp.getDuration());
        holder.vhtiming.setText(modeltemp.getTime());
        holder.vhroom.setText(modeltemp.getRoom());



    }

    @Override
    public int getItemCount() {
        return ptList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vhclass,vhduration,vhtiming,vhroom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vhclass=(TextView) itemView.findViewById(R.id.item_class);
            vhtiming=(TextView) itemView.findViewById(R.id.item_timing);
            vhduration=(TextView) itemView.findViewById(R.id.item_duration);
            vhroom=(TextView) itemView.findViewById(R.id.item_room);
        }
    }
}
