package com.example.clickit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void btn_start(View view){
        //TODO 这里面放所需要执行的程序代码
        Intent intent = new Intent();
        intent.setClass(this, MainGameActivity.class);
        startActivity(intent);
        this.finish();
    }
}
