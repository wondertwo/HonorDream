package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cheetah.honordream.R;

/**
 * 开屏广告
 *
 * Created by wondertwo on 2017/7/22.
 */

public class LauncherActivity extends Activity {

    private Button mLauncherToHome;
    private Button mLauncherToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);

        mLauncherToHome = (Button) findViewById(R.id.activity_launcher_to_home);
        mLauncherToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherActivity.this, ContainerActivity.class));
                finish();
            }
        });

        mLauncherToLogin = (Button) findViewById(R.id.activity_launcher_to_login);
        mLauncherToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                finish();
            }
        });


    }
}
