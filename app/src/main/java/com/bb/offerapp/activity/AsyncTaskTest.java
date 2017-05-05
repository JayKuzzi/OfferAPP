package com.bb.offerapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.util.MyAsyncTask;

public class AsyncTaskTest extends Activity implements View.OnClickListener {

    private ProgressBar myProgressBar;
    private Button start, cancel;
    private TextView textView;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
        init();
        start.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void init() {
        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        start = (Button) findViewById(R.id.start);
        cancel = (Button) findViewById(R.id.cancel);
        textView = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //启动异步任务，并传入"Begin","End"值演示UI线程向后端线程传值的情况
                mTask = new MyAsyncTask(myProgressBar, textView, this);
                mTask.execute("Begin", "End");
                start.setEnabled(false);
                cancel.setEnabled(true);
                break;
            case R.id.cancel:
                if (mTask != null) {
                    mTask.cancel(true);
                    start.setEnabled(true);
                    cancel.setEnabled(false);
                } else {
                    Toast.makeText(this, "您还没有开始", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTask.isCancelled()) {
            return;
        }
        mTask.cancel(true);
    }
}
