package com.example.apoxeo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiInterface {
    @GET("place/nearbysearch/json?location=12.97,77.59&radius=5000&type=hindu_temple&key=AIzaSyAD90QUFl0kvHLyR98BmnivBEz79eGRisc")
    Call<ResponseBody> getData();

}
