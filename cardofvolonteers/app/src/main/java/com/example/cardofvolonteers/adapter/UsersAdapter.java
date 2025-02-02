package com.example.cardofvolonteers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardofvolonteers.Model.User;
import com.example.cardofvolonteers.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private Context cntx;
    private List<User> userList;

    public UsersAdapter(Context cntx, List<User> userList) {
        this.cntx = cntx;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cntx).inflate(R.layout.fragment_list_of_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = userList.get(position);
        if (!user.getStatusWork()) holder.tvstatus.setText("Занят");
        holder.tvname.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView tvname, tvstatus;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.name);
            tvstatus = itemView.findViewById(R.id.status);
        }
    }
}
