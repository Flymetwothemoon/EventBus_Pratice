package com.example.eventbus_pratice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    private Button register;
    private Button bt_sticky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        register = findViewById(R.id.register);
        bt_sticky = findViewById(R.id.bt_sticky);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new com.example.eventbus_pratice.EventBus("事件发送成功!!!"));
                finish();
            }
        });
        bt_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new com.example.eventbus_pratice.EventBus("黏性事件发送成功!!!"));
                finish();
            }
        });
    }
}