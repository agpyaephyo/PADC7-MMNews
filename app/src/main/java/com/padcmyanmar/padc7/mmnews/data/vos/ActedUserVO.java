package com.padcmyanmar.padc7.mmnews.data.vos;

import com.google.gson.annotations.SerializedName;

public class ActedUserVO {

    @SerializedName("user-id")
    private String userId;

    @SerializedName("user-name")
    private String userName;

    @SerializedName("profile-image")
    private String profileImage;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
