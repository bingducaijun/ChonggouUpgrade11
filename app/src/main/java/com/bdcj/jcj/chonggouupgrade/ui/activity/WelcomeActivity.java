package com.bdcj.jcj.chonggouupgrade.ui.activity;

import android.os.Bundle;

import com.bdcj.jcj.chonggouupgrade.R;
import com.bdcj.jcj.chonggouupgrade.business.entity.SendToUIEty;
import com.bdcj.jcj.chonggouupgrade.common.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void onSuccess(SendToUIEty successEty) {

    }

    @Override
    public void onFailure(SendToUIEty failureEty) {

    }
}
