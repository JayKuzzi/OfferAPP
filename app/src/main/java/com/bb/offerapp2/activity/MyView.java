package com.bb.offerapp2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bb.offerapp2.R;

/**
 * Created by bb on 2017/4/17.
 */

public class MyView extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview);
    }
}
