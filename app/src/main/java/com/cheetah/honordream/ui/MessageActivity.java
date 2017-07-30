package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cheetah.honordream.R;

/**
 * 消息页面
 *
 * Created by wondertwo on 2017/7/24.
 */

public class MessageActivity extends Activity {

    private ImageButton mMessageBack;
    private ImageButton mMessageShop;
    private TextView mMessageExchangeNow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mMessageBack = (ImageButton) findViewById(R.id.message_back);
        mMessageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, ContainerActivity.class));
            }
        });

        //跳转到(对方的)个人店铺
        mMessageShop = (ImageButton) findViewById(R.id.message_shop);
        mMessageShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, OtherShopActivity.class));
            }
        });

        //跳转到勾选页
        mMessageExchangeNow = (TextView) findViewById(R.id.message_exchange_now);
        mMessageExchangeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, CheckItemActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MessageActivity.this, ContainerActivity.class));
    }
}
