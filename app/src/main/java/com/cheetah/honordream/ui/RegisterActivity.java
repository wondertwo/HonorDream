package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cheetah.honordream.R;

import static com.cheetah.honordream.R.id.register_register;

/**
 *
 * Created by wondertwo on 2017/7/23.
 */

public class RegisterActivity extends Activity {

    private Button mRegisterBtn;
    private TextView mLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register();
        toLogin();
    }

    /**
     * 跳转到登录
     */
    private void toLogin() {
        mLoginBtn = (TextView) findViewById(R.id.register_to_login);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 注册
     */
    private void register() {
        mRegisterBtn = (Button) findViewById(register_register);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, ContainerActivity.class);
                startActivity(intent);
            }
        });
    }
}
