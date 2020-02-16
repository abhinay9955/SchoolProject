package com.example.schoolproject.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;

import java.util.ArrayList;

public class ParentAddChildAdapter extends RecyclerView.Adapter <ParentAddChildAdapter.MyViewHolder> {


    ArrayList<StudentModel> children;
    Context context;


    public ParentAddChildAdapter(ArrayList<StudentModel> children)
    {
        this.children=children;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_child_item_pa,parent);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(position,holder);
            }
        });


    }


    public void showDialog(int position,MyViewHolder holder)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context).setMessage("Are you Sure About adding ");
    }

    @Override
    public int getItemCount() {
        return children.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,roll,std,parent;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            roll=itemView.findViewById(R.id.roll);
            std=itemView.findViewById(R.id.std);
            parent=itemView.findViewById(R.id.parent);
            card=itemView.findViewById(R.id.card);

        }
    }
}
