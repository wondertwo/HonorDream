package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cheetah.honordream.R;

/**
 * 登录注册
 *
 * Created by wondertwo on 2017/7/22.
 */

public class LoginActivity extends Activity {

    private Button mLoginBtn;
    private TextView mRegisterBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
        toRegister();
    }

    /**
     * 跳转到注册
     */
    private void toRegister() {
        mRegisterBtn = (TextView) findViewById(R.id.login_to_register);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 登录
     */
    private void login() {
        mLoginBtn = (Button) findViewById(R.id.login_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
                startActivity(intent);
            }
        });
    }
}
