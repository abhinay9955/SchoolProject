package com.example.schoolproject.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.TutorAppointmentModel;
import com.example.schoolproject.R;

import java.util.ArrayList;

public class AppointmentRviewAddapter extends RecyclerView.Adapter<AppointmentRviewAddapter.ViewHolder>
{
    private  ArrayList<TutorAppointmentModel> mylist;
    private Context ctx;

    public AppointmentRviewAddapter(Context ctx) {
        this.ctx = ctx;
    }

    public AppointmentRviewAddapter(ArrayList<TutorAppointmentModel> mylist) {
        this.mylist = mylist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.childitem_tappointment,parent,false);

        return new AppointmentRviewAddapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        TutorAppointmentModel tempmodel=mylist.get(position);
        holder.tofappoint.setText(tempmodel.getTofAppointment());
        holder.parentcontact.setText(tempmodel.getParentcontact());
        holder.parentname.setText(tempmodel.getParentname());

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView parentname,parentcontact,tofappoint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentname=(TextView) itemView.findViewById(R.id.tapp_parentname);
            parentcontact=(TextView) itemView.findViewById(R.id.tapp_parentcontact);
            tofappoint=(TextView) itemView.findViewById(R.id.tapp_timing);
        }
    }
}
