package com.cheetah.honordream.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cheetah.honordream.R;

/**
 * 消息列表
 *
 * Created by wondertwo on 2017/7/27.
 */

public class MsgListActivity extends Activity {

    private ImageButton mMsgListBack;
    private LinearLayout mMsgListItem1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_list);

        mMsgListBack = (ImageButton) findViewById(R.id.activity_msg_list_back);
        mMsgListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MsgListActivity.this, ContainerActivity.class));
            }
        });

        mMsgListItem1 = (LinearLayout) findViewById(R.id.activity_msg_lst_item_1);
        mMsgListItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到交易页一
                startActivity(new Intent(MsgListActivity.this, ExchangeActivity.class));
            }
        });
    }
}
