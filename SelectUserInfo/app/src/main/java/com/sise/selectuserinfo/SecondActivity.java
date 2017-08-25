package com.sise.selectuserinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    public Button user1btn, user2btn, user3btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setEvents();

    }

    private void setEvents() {
        user1btn.setOnClickListener(this);
        user2btn.setOnClickListener(this);
        user3btn.setOnClickListener(this);
    }

    private void initViews() {
        user1btn = (Button) findViewById(R.id.user1btu);
        user2btn = (Button) findViewById(R.id.user2btu);
        user3btn = (Button) findViewById(R.id.user3btu);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.user1btu:
                String data1 = "111";
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                intent1.putExtra("userinfo", data1);
                startActivity(intent1);
                break;
            case R.id.user2btu:
                String data2 = "222";
                Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                intent2.putExtra("userinfo", data2);
                startActivity(intent2);
                break;
            case R.id.user3btu:
                String data3 = "333";
                Intent intent3 = new Intent(SecondActivity.this, MainActivity.class);
                intent3.putExtra("userinfo", data3);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }




}
