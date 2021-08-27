package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leaderboard.models.User;

import java.util.List;

public class leaderBoard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<User> userList;
    private UsersRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Intent intent=getIntent();
        userList=(List<User>) intent.getSerializableExtra("users");
        initiateWidgets();
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UsersRecyclerAdapter(this,userList);
        recyclerView.setAdapter(adapter);
    }

    private void initiateWidgets() {
        recyclerView=findViewById(R.id.recycler_view);
    }
}