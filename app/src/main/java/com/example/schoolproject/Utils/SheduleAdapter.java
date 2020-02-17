package com.example.schoolproject.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.TutorModel;
import com.example.schoolproject.R;

import java.util.ArrayList;

public class SheduleAdapter extends RecyclerView.Adapter<SheduleAdapter.ViewHolder> {
    ArrayList<TutorModel> listtem;
    Context ctx;

    public SheduleAdapter(ArrayList<TutorModel> listtem, Context ctx) {
        this.listtem = listtem;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shed_update,parent,false);

        return new  SheduleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final TutorModel temp=listtem.get(position);
        holder.tcontact.setText(temp.getContact());
        holder.tname.setText(temp.getName());
        holder.tId.setText(temp.getTutorid());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(temp);
            }
        });


    }

    public void showDialog(TutorModel tutor)
    {

    }

    @Override
    public int getItemCount() {
        return listtem.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView tname,tcontact,tId;
        Button add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tname=(TextView) itemView.findViewById(R.id.su_name);
            tcontact=(TextView) itemView.findViewById(R.id.su_contact);
            tId=(TextView) itemView.findViewById(R.id.su_tid);
            add=itemView.findViewById(R.id.su_add_button);
        }
    }
}
