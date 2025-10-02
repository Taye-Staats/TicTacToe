package com.example.tictactoe;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupaBaseClient {
    public static SupabaseService getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhkZHJmaWVla3BtaGt6bGNxbXVsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTk0MzI5MDksImV4cCI6MjA3NTAwODkwOX0.3mFkp5M1fWimqozeZB_Do2H_nz1BA8cs693bZwE4KwI")
                            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhkZHJmaWVla3BtaGt6bGNxbXVsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTk0MzI5MDksImV4cCI6MjA3NTAwODkwOX0.3mFkp5M1fWimqozeZB_Do2H_nz1BA8cs693bZwE4KwI")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://xddrfieekpmhkzlcqmul.supabase.co/rest/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SupabaseService.class);
    }
}
