package com.comp313sec401.group4.shovelhero;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class MainAdapter extends FirebaseRecyclerAdapter<User, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull User model) {

        // System.out.println("Model: " + model.getFname());
        holder.name.setText(model.getFname());
        holder.accountType.setText(model.getAccountType());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView name, accountType;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txtName);
            accountType = (TextView) itemView.findViewById(R.id.txtAccountType);
        }
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        Log.d("FirebaseData", "Data successfully retrieved from Firebase.");
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        super.onError(error);
        Log.e("FirebaseError", "Failed to retrieve data from Firebase: " + error.getMessage());
    }
}
