package com.swanson.octodroid;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("login")
    public String username;

    public Owner(String username) {
        this.username = username;
    }

    public boolean isCool() {
        return username == "swanson";
    }
}
