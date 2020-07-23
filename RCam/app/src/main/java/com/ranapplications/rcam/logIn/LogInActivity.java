package com.ranapplications.rcam.logIn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ranapplications.rcam.global.Global;
import com.ranapplications.rcam.R;

public class LogInActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Global.hideStustusBar(this, R.color.statusBarColor, R.color.navigationBarColor);

        init();
    }

    private void init(){
        Fragment fragment = new LogInFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}
