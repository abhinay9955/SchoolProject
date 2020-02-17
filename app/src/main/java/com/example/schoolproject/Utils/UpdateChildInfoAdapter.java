

package com.example.schoolproject.Utils;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.schoolproject.Model.StudentModel;
        import com.example.schoolproject.R;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.HashMap;

public class UpdateChildInfoAdapter extends RecyclerView.Adapter <UpdateChildInfoAdapter.MyViewHolder> {


    ArrayList<StudentModel> children;
    Context context;


    public UpdateChildInfoAdapter(ArrayList<StudentModel> children)
    {
        this.children=children;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_child_item_pa,parent,false);
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
        holder.parent.setText(children.get(position).getParent());
        holder.name.setText(children.get(position).getName());
        holder.roll.setText(children.get(position).getRoll());
        holder.std.setText(children.get(position).getStd());


    }


    public void showDialog(final int position, MyViewHolder holder)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context).setMessage("Are you Sure About adding ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HashMap<String,Object> data=new HashMap<>();
                        data.put("std",children.get(position).getStd());
                        data.put("id",children.get(position).getId());
                        FirebaseDatabase.getInstance().getReference("Parent").child(FirebaseAuth.getInstance().getUid()).child("children").push().updateChildren(data);
                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog.create().show();
    }

    @Override
    public int getItemCount() {

        //Log.i("getItemCount: ",children.size()+"");
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
