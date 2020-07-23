package com.ranapplications.rcam.mainActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ranapplications.rcam.GetTheCurrentToken;
import com.ranapplications.rcam.global.Global;
import com.ranapplications.rcam.R;
import com.ranapplications.rcam.global.MakeVisible;
import com.ranapplications.rcam.logIn.LogInActivity;
import com.ranapplications.rcam.mainActivity.alertsFragment.AlertsFragment;
import com.ranapplications.rcam.mainActivity.mainFragment.MainFragment;

/**
 *
 * Project RID : R4-O7
 *
 *
 * Copyright 2020 RAN. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Developed by Ran Orel Ben David
 *
 * The "R-Cam" logo(s), icons and any part of the code in this project
 * (this DOES NOT refer to external libraries used in this project OR Firebase) made by Ran Ben David.
 *
 *
 * RAN 2020
 */


public class MainActivity extends AppCompatActivity implements GetAllDataForMainScreen.GetAllDataForMainScreenCallBack, MakeVisible {

    private final int OPEN_MAIN_FRAGMENT= 1;
    private final int OPEN_ADD_NEW_CAMERA_PAGE= 2;
    private Fragment mainFragment, alertsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.hideStustusBar(this, R.color.statusBarColor, R.color.navigationBarColor);

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, LogInActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.loading_page);

        new GetAllDataForMainScreen(this);

        new GetTheCurrentToken(getApplicationContext());
    }


    private void updateTheUI(int UPDATE_ID){
        switch (UPDATE_ID){
            case OPEN_MAIN_FRAGMENT:
                setContentView(R.layout.activity_main);
                setUpBottomNavigationView();
                break;
            case OPEN_ADD_NEW_CAMERA_PAGE:
                // TODO: 2020-06-03  ADD THIS PAGE
                break;

                default: break;
        }
    }

    private void setUpBottomNavigationView(){
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(mainFragment == null)
            mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mainFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigationMain:
//                    fragment = new MainFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mainFragment).commit();
                    return true;

                    case R.id.navigationAlerts:
                        if (alertsFragment == null)
                            alertsFragment = new AlertsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, alertsFragment).commit();
                        return true;

            }
            return false;
        }
    };

    @Override
    public void dataForMainScreenCallBack() {
        updateTheUI(OPEN_MAIN_FRAGMENT);
    }

    @Override
    public void dataForMainScreenCallBackEmptyCameraList() {
        updateTheUI(OPEN_ADD_NEW_CAMERA_PAGE);
    }


    private void transparentStatusAndNavigation() {
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.navigationBarColor));
        }
    }

    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void makeVisible() {
        FrameLayout frameLayoutScreen = findViewById(R.id.frameLayoutScreen);
        frameLayoutScreen.setVisibility(View.GONE);
    }
}
