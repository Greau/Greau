package com.example.a14161.guochuang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 14161 on 2017/9/11.
 */

public class WupinInforActivity extends AppCompatActivity{
    Button friendSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thing_form);


        friendSet=(Button)findViewById(R.id.setFriend);
        friendSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WupinInforActivity.this,FriendSettingActivity.class);
                startActivity(intent);
            }
        });
    }

}
