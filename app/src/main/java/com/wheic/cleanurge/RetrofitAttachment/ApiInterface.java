package com.wheic.cleanurge.RetrofitAttachment;

import com.wheic.cleanurge.ModelResponse.Beacon.BeaconListResponse;
import com.wheic.cleanurge.ModelResponse.Registration.LoginResponse;
import com.wheic.cleanurge.ModelResponse.Registration.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/user/register")
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") int phone,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("api/user/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

//    @GET("api/user/{id}")
//    Call<UserResponse> getUID(
//            @Path("id") String uID,
//            @Header("Authorization") String authToken);

    @GET("api/beacon")
    Call<BeaconListResponse> fetchBeacons(@Header("Authorization") String authToken);


}
