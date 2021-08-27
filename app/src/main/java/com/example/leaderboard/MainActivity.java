package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leaderboard.models.ApiResponse;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText idEditText;
    private Button submitButton;
    private String userId;
    private jsonPlaceHolderApi jsonPlaceHolderApi1;
    private String TAG = "main activity";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateWidgets();
        initiateRetrofit();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = idEditText.getText().toString();
                if (userId.isEmpty()) {
                    Toast.makeText(MainActivity.this, "you have to enter your id", Toast.LENGTH_SHORT).show();
                    return;
                }
                getUsers(userId);

            }
        });
    }

    private void initiateRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-leaderboard-b8561.cloudfunctions.net/expressApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi1 = retrofit.create(jsonPlaceHolderApi.class);
    }

    private void getUsers(String userId) {
        progressBar.setVisibility(View.VISIBLE);
        Call<ApiResponse> call = jsonPlaceHolderApi1.getUsers(userId);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse() returned: " + response);
                    Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                ApiResponse apiResponse = response.body();
                if (!apiResponse.getMessage().equals("succeded")) {
                    Toast.makeText(MainActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                Intent intent = new Intent(MainActivity.this, leaderBoard.class);
                intent.putExtra("users", (Serializable) response.body().getDisplayedUsers());
                progressBar.setVisibility(View.GONE);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure() returned: " + t.getMessage());
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void initiateWidgets() {
        idEditText = findViewById(R.id.id_edittext);
        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.main_progress);
    }
}