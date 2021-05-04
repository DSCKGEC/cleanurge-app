package com.wheic.cleanurge.RetrofitAttachment;

import com.wheic.cleanurge.ModelResponse.Beacon.BeaconListResponse;
import com.wheic.cleanurge.ModelResponse.Registration.LoginResponse;
import com.wheic.cleanurge.ModelResponse.Registration.RegisterResponse;
import com.wheic.cleanurge.ModelResponse.Reports.ReportPostResponse;
import com.wheic.cleanurge.ModelResponse.Reports.ReportUserGetResponse;
import com.wheic.cleanurge.ModelResponse.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/user/register")
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("api/user/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/beacon")
    Call<BeaconListResponse> fetchBeacons(@Header("Authorization") String authToken);

    @FormUrlEncoded
    @POST("api/report/create")
    Call<ReportPostResponse> addReports(
            @Header("Authorization") String authToken,
            @Field("content") String content,
            @Field("picture_url") String imageUrl,
            @Field("address") String address
    );

    @GET("api/report/user/{id}")
    Call<ReportUserGetResponse> getUserReports(
            @Header("Authorization") String authToken,
            @Path("id") String uID
    );


}
