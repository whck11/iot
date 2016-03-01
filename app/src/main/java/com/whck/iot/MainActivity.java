package com.whck.iot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whck.iot.model.LocalUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalUtil.PREFERENCES = getSharedPreferences(LocalUtil.LOCAL_KEY, Context.MODE_PRIVATE);
        LocalUtil.EDITOR = LocalUtil.PREFERENCES.edit();
    }
}
