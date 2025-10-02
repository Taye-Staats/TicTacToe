package com.example.tictactoe;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SupabaseService {
    @GET("ping_test?select=*") // Replace with a real table name or use a dummy one
    Call<ResponseBody> ping();
}
