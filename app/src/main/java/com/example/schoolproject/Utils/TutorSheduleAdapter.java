package com.example.schoolproject.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.SheduleModel;
import com.example.schoolproject.R;

import java.util.ArrayList;

public class TutorSheduleAdapter extends RecyclerView.Adapter<TutorSheduleAdapter.ViewHolder> {
    private ArrayList<SheduleModel> mylist;
    private Context ctx;

    public TutorSheduleAdapter(ArrayList<SheduleModel> mylist, Context ctx) {
        this.mylist = mylist;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shedule_model,parent,false);

        return new TutorSheduleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        SheduleModel temp=mylist.get(position);
        holder.subject.setText(temp.getSubject());
        holder.classg.setText(temp.getClassG());
        holder.period.setText(temp.getPeriod());

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView period,classg,subject;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            period=(TextView) itemView.findViewById(R.id.ism_period);
            classg=(TextView) itemView.findViewById(R.id.ism_class);
            subject=(TextView) itemView.findViewById(R.id.ism_subject);
         }
    }


}
