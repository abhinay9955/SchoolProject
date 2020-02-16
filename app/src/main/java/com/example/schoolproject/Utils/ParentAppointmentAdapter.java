package com.example.schoolproject.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Model.ParentAppointmentModel;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentAppointmentAdapter extends RecyclerView.Adapter<ParentAppointmentAdapter.MyViewHolder> {

    ArrayList<ParentAppointmentModel> tutors;
    Context context;

    public ParentAppointmentAdapter(ArrayList<ParentAppointmentModel> tutors)
    {
        this.tutors=tutors;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_parent,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {

        holder.name.setText(tutors.get(i).getName());
        holder.tutorid.setText(tutors.get(i).getTutorid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(i);
            }
        });

    }

    public void showDialog(final int i)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context).setMessage("Book an Appointment with \n"+tutors.get(i).getName()+"\non next PTM")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, final int i) {
                        FirebaseDatabase.getInstance().getReference("nextpta").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                HashMap<String,Object> map=new HashMap<>();
                                map.put("pid",FirebaseAuth.getInstance().getUid());
                                map.put("date",dataSnapshot.getValue(String.class));
                                FirebaseDatabase.getInstance().getReference("Tutor").child(tutors.get(i).getId()).child("Appointments").push().updateChildren(map);
                                Toast.makeText(context, "Booked", Toast.LENGTH_SHORT).show();
                                 }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        alertDialog.create().show();

    }

    @Override
    public int getItemCount() {
        return tutors.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,tutorid;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            tutorid=itemView.findViewById(R.id.tutorid);
        }
    }
}
