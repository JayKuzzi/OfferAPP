package com.bb.offerapp.util;

/**
 * Created by bb on 2017/4/26.
 */

public
interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}