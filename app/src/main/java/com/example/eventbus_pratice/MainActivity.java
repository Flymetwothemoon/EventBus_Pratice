package com.example.eventbus_pratice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_message;
    private Button bt_message;
    private Button bt_subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = findViewById(R.id.tv_message);
        bt_message = findViewById(R.id.bt_message);
        bt_subscription = findViewById(R.id.bt_subscription);
        bt_subscription.setOnClickListener(this);
        bt_message.setOnClickListener(this);
        tv_message.setText("MainActivity");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(EventBus eventBus){
        tv_message.setText(eventBus.getMessage());
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bt_message){
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }
        else if(view.getId()==R.id.bt_subscription){
            if(!org.greenrobot.eventbus.EventBus.getDefault().isRegistered(this)) {
                org.greenrobot.eventbus.EventBus.getDefault().register(MainActivity.this);
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void ononMoonStickyEvent(EventBus eventBus){
        tv_message.setText(eventBus.getMessage());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);

    }

}