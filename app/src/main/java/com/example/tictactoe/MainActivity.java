package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SupabaseService service = SupaBaseClient.getService();

        service.ping().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Supabase", "Connected! Status: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String json = response.body().string();  // Only call .string() once
                        Log.d("Supabase", "Response: " + json);
                    } catch (IOException e) {
                        Log.e("Supabase", "Error reading response", e);
                    }
                } else {
                    Log.e("Supabase", "Response failed or empty");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Supabase", "Connection failed: " + t.getMessage());
            }
        });
    }
    public void playButtonClick(View view)
    {
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);
    }
}