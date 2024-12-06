package com.comp313sec401.group4.shovelhero.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp313sec401.group4.shovelhero.Models.User;
import com.comp313sec401.group4.shovelhero.Models.WorkOrder;
import com.comp313sec401.group4.shovelhero.R;

import java.util.ArrayList;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public TempAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.orderentry,parent,false);
        return new MyViewHolder(v);    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       User user = list.get(position);
       holder.name.setText(user.getFirstName());
       holder.email.setText(user.getEmail());
       holder.phone.setText(user.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView name, email, phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email= itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
