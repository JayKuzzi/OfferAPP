package com.bb.offerapp2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bb.offerapp2.R;
import com.bb.offerapp2.util.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private CheckBox checkBox;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        checkBox= (CheckBox) findViewById(R.id.checkbox1);

        //取数据
        sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
        boolean isRemember =sharedPreferences.getBoolean("remember",false);
        if(isRemember){
            String account=sharedPreferences.getString("account","");
            String password=sharedPreferences.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
        }

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 如果账号是admin且密码是123456，就认为登录成功
                if (account.equals("admin") && password.equals("123456")) {
                    editor=sharedPreferences.edit();
                    if(checkBox.isChecked()){
                        editor.putBoolean("remember",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, SendBroadcast.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
