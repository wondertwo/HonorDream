package com.cheetah.honordream.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cheetah.honordream.R;

/**
 * 容器界面
 *
 * Created by wondertwo on 2017/7/22.
 */
public class ContainerActivity extends FragmentActivity {

    private static final String FRAGMENT_HOME = "HomeFragment";
    private static final String FRAGMENT_PERSON = "PersonFragment";

    private String fragment_home = "com.cheetah.honordream.ui.fragment.HomeFragment";
    private String fragment_person = "com.cheetah.honordream.ui.fragment.PersonFragment";

    private RadioButton mTabbarHome;
    private RadioButton mTabbarPublish;
    private RadioButton mTabbarPerson;
    private RadioGroup mTabbarRadioGroup;

    private Fragment mFragment;
    private int fc = R.id.fragment_container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mTabbarRadioGroup = (RadioGroup) findViewById(R.id.tab_bar_radio_group);
        mTabbarRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                int selectedId = group.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.tab_bar_home:
                        switchFragment(fm, ft, FRAGMENT_HOME, fragment_home, "二货");
                        break;
                    case R.id.tab_bar_null:
                        break;
                    case R.id.tab_bar_person:
                        switchFragment(fm, ft, FRAGMENT_PERSON, fragment_person, "我的");
                        break;
                    default:
                        break;
                }
            }
        });

        mTabbarHome = (RadioButton) findViewById(R.id.tab_bar_home);
        mTabbarHome.setChecked(true); // Tab栏默认选中二货

        //发布按钮点击事件
        mTabbarPublish = (RadioButton) findViewById(R.id.tab_bar_publish);
        mTabbarPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContainerActivity.this, PublishActivity.class);
                startActivity(intent);
            }
        });

        //切换Fragment逻辑
        checkFragmentState();
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }


    // 切换Fragment
    private void switchFragment(FragmentManager fm, FragmentTransaction ft, String tag, String cname, String title) {
        checkFragmentState();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment != null) {
            ft.hide(mFragment).show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, cname);
            ft.add(fc, fragment, tag);
            ft.hide(mFragment).show(fragment).commit();
        }
        mFragment = fragment;
    }

    // 检查mFragment是否已经创建
    private void checkFragmentState() {
        if (mFragment == null) {
            mFragment = Fragment.instantiate(this, "com.cheetah.honordream.ui.fragment.HomeFragment");
            getSupportFragmentManager().beginTransaction().add(fc, mFragment, FRAGMENT_HOME).commit();
        }
    }
}
