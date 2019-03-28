package com.padcmyanmar.padc7.mmnews.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;

public class LoginResponse extends BaseResponse {

    @SerializedName("login_user")
    private LoginUserVO loginUser;

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}
