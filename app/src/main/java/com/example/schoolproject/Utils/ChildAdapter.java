package com.example.schoolproject.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.MyAdapterPT;
import com.example.schoolproject.R;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>

{
    private ArrayList<StudentModel> mylist;
    private Context ctx;

    public ChildAdapter(ArrayList<StudentModel> mylist) {
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.ctx=parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_item,parent,false);
        return new ChildAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        StudentModel modeltemp=mylist.get(position);
        holder.vhchildname.setText(modeltemp.getName());
        holder.vhchildparenntname.setText(modeltemp.getParent());
        holder.vhchildrank.setText((modeltemp.getRank()==-1?"N/A":modeltemp.getRank()+""));
        holder.vhchildtwd.setText((modeltemp.getTwd()==-1?"N/A":modeltemp.getTwd()+""));
        holder.vhchildtpd.setText((modeltemp.getTdp()==-1?"N/A":modeltemp.getTdp()+""));
        holder.vhchildroll.setText(modeltemp.getRoll());
        holder.vhchildgrade.setText((modeltemp.getGrade()==-1?"N/A":modeltemp.getGrade()+""));
        holder.vhchildcontact.setText(modeltemp.getContact());
        holder.vhchildclass.setText(modeltemp.getStd());




    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vhchildname,vhchildparenntname,vhchildcontact,vhchildID,vhchildroll,vhchildgrade,vhchildtwd,vhchildtpd,vhchildclass,
                        vhchildrank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vhchildname =(TextView) itemView.findViewById(R.id.child_item_name);
            vhchildclass =(TextView) itemView.findViewById(R.id.child_item_class);
            vhchildcontact =(TextView) itemView.findViewById(R.id.child_item_contact);
            vhchildgrade =(TextView) itemView.findViewById(R.id.child_item_grade);
            //vhchildID =(TextView) itemView.findViewById(R.id.);
            vhchildparenntname =(TextView) itemView.findViewById(R.id.child_item_guardian);
            vhchildroll =(TextView) itemView.findViewById(R.id.child_item_rollno);
            vhchildtpd =(TextView) itemView.findViewById(R.id.child_item_tdp);
            vhchildtwd =(TextView) itemView.findViewById(R.id.child_item_twd);
            vhchildrank =(TextView) itemView.findViewById(R.id.child_item_rank);
        }
    }
}
