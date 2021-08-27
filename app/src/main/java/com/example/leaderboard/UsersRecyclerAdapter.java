package com.example.leaderboard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.models.User;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.viewHolder> {
    private Context context;
    private List<User> userList;

    public UsersRecyclerAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.rank.setText(String.valueOf(userList.get(position).getRank()));
        holder.name.setText(userList.get(position).getName());
        holder.bananas.setText(String.valueOf(userList.get(position).getBananas()));
        if(userList.get(position).getCurrentUser()){
            holder.itemView.setBackgroundResource(R.drawable.current_user_item);
            holder.currentUser.setVisibility(View.VISIBLE);
            holder.currentUser.setBackgroundResource(R.color.purple_200);
            holder.currentUser.setTextColor(Color.WHITE);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#6C63FF"));
            holder.rank.setTextColor(Color.WHITE);
            holder.rank.setBackgroundResource(R.color.purple_200);
            holder.name.setBackgroundResource(R.color.purple_200);
            holder.bananas.setBackgroundResource(R.color.purple_200);
            holder.name.setTextColor(Color.WHITE);
            holder.bananas.setTextColor(Color.WHITE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,userList.get(position).getCurrentUser().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView rank;
        private TextView name;
        private TextView bananas;
        private TextView currentUser;
        private LinearLayout linearLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.user_rank);
            name = itemView.findViewById(R.id.user_name);
            bananas = itemView.findViewById(R.id.user_bananas);
            currentUser = itemView.findViewById(R.id.current_user);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
