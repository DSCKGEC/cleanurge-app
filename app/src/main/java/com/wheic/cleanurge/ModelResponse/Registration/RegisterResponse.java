package com.wheic.cleanurge.ModelResponse.Registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wheic.cleanurge.ModelResponse.User;

public class RegisterResponse {
    @SerializedName("user")
    @Expose
    private User user;

    public RegisterResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
