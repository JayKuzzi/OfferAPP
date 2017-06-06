package com.bb.offerapp2.util;

/**
 * Created by bb on 2017/4/26.
 */

public interface HttpCientCallback {
    void onFinish(byte[] response);
    void onError(Exception e);
}
