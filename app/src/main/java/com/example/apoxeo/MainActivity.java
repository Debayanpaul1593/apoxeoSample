package com.example.apoxeo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        try{
            setUpRecyclerView();
            callService();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void callService() {
        try {
            Retrofit retrofit = ApiClient.getClient();
            ApiInterface api = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = api.getData();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String str = null;
                        try {
                            str = response.body().string();
                        } catch (IOException e) {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (str != null) {
                            JsonElement jsonRaw = new JsonParser().parse(str);
                            JsonArray arr = jsonRaw.getAsJsonObject().get("results").getAsJsonArray();
                            progress.setVisibility(View.GONE);
                            adapter.setData(arr);
                        }
                    } catch (Exception e) {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setUpRecyclerView() {
        try {
            progress = findViewById(R.id.progress);
            recyclerView = findViewById(R.id.recyclerView);
            adapter = new CustomAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}