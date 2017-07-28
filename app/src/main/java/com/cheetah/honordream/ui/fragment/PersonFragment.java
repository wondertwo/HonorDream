package com.cheetah.honordream.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cheetah.honordream.R;
import com.cheetah.honordream.ui.LoginActivity;
import com.cheetah.honordream.ui.MyShopActivity;

/**
 * 个人中心
 *
 * Created by wondertwo on 2017/7/24.
 */

public class PersonFragment extends Fragment {

    private Button mLoginNowBtn;
    private LinearLayout mMyShop;
    //private LinearLayout mMsgLst;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View personView = inflater.inflate(R.layout.fragment_person, container, false);

        initializeUI(personView);
        return personView;
    }

    private void initializeUI(View personView) {
        mLoginNowBtn = (Button) personView.findViewById(R.id.fragment_person_login);
        mLoginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mMyShop = (LinearLayout) personView.findViewById(R.id.fragment_person_my_shop);
        mMyShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyShopActivity.class);
                startActivity(intent);
            }
        });

        /*mMsgLst = (LinearLayout) personView.findViewById(R.id.fragment_person_msg_list);
        mMsgLst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MsgListActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
