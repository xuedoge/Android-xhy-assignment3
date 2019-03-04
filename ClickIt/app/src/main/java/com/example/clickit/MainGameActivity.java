package com.example.clickit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainGameActivity extends AppCompatActivity {

    int x;
    int y;
    static int win = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        x=0;
        y=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void btn_l_onclick(View view){
        //TODO 这里面放所需要执行的程序代码
        x++;
        String s = x+":"+y;
        ((TextView) findViewById(R.id.tv_score)).setText(s);
        if(x==win){
            Toast showToast=Toast.makeText(this, "lion is winner", Toast.LENGTH_SHORT);
            showToast.setGravity(Gravity.CENTER, 0, 0);
            showToast.show();

            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
    public void btn_r_onclick(View view){
        //TODO 这里面放所需要执行的程序代码
        y++;
        String s = x+":"+y;
        ((TextView) findViewById(R.id.tv_score)).setText(s);
        if(y==win){
            Toast showToast=Toast.makeText(this, "tiger is winner", Toast.LENGTH_SHORT);
            showToast.setGravity(Gravity.CENTER, 0, 0);
            showToast.show();

            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

}
