package com.example.schoolproject.Utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.TutorModel;
import com.example.schoolproject.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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

    public void showDialog(final TutorModel tutor)
    {
        final Dialog dialog=new Dialog(ctx);
        dialog.setContentView(R.layout.sheduledialoglayout);
        final TextView name,tutorid;
        final EditText std,period,sub;
        Button add,cancel;
        name=dialog.findViewById(R.id.su_name);
        tutorid=dialog.findViewById(R.id.su_tid);
        std=dialog.findViewById(R.id.sdd_class);
        period=dialog.findViewById(R.id.sdd_period);
        sub=dialog.findViewById(R.id.sdd_subject);
        add=dialog.findViewById(R.id.su_add_button);
        cancel=dialog.findViewById(R.id.sdd_cancelbutton);
        name.setText(tutor.getName());
        tutorid.setText(tutor.getTutorid());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(TextUtils.isEmpty(std.getText().toString()) || TextUtils.isEmpty(period.getText().toString()) || TextUtils.isEmpty(sub.getText().toString()))
                 {
                     Toast.makeText(ctx, "All fields are required", Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     HashMap<String,Object> data=new HashMap<>();
                     data.put("class",std.getText().toString());
                     data.put("period",period.getText().toString());
                     data.put("subject",sub.getText().toString());
                     FirebaseDatabase.getInstance().getReference("Tutor").child(tutor.getId()).child("schedule").push().updateChildren(data);
                     Toast.makeText(ctx, "Schedule Updated", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                 }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

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
