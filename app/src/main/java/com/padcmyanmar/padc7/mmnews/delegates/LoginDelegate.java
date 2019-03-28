package com.padcmyanmar.padc7.mmnews.delegates;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;

public interface LoginDelegate extends BaseNetworkDelegate {

    void onSuccess(LoginUserVO loginUser);
}
