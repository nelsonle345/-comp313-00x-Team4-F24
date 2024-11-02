package com.comp313sec401.group4.shovelhero.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import com.comp313sec401.group4.shovelhero.Models.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;

//import com.comp313sec401.group4.shovelhero.ApprovedWorkOrderActivity;
import com.comp313sec401.group4.shovelhero.R;

public class ListShovelorAcceptedOrdersAdapter extends RecyclerView.Adapter<ListShovelorAcceptedOrdersAdapter.ViewHolder>{
    private Context context;
    private List<User> userList;

    public ListShovelorAcceptedOrdersAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.accepted_work_order_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userNameTV.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        holder.userEmailTV.setText("Email: " + user.getEmail());
        holder.userPhoneTV.setText("Phone: " + user.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userNameTV, userEmailTV, userPhoneTV;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTV = itemView.findViewById(R.id.tvAcceptedUserName);
            userEmailTV = itemView.findViewById(R.id.tvAcceptedUserContact);
            userPhoneTV = itemView.findViewById(R.id.tvAcceptedWorkDetails);
        }
    }
}
