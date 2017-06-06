package com.bb.offerapp2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bb.offerapp2.R;
import com.bb.offerapp2.util.BaseActivity;

public class SendBroadcast extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        Button data = (Button) findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendBroadcast.this,DatabaseTest.class);
                startActivity(intent);
            }
        });

    }

}
