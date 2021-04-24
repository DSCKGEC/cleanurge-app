package com.wheic.cleanurge.ModelResponse.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user")
    @Expose
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
