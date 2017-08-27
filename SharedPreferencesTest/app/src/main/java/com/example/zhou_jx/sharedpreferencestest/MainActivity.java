package com.example.zhou_jx.sharedpreferencestest;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private Button saveData, restoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveData = (Button) findViewById(R.id.save_data);
        restoreData = (Button) findViewById(R.id.restore_data);

        MyListener listener = new MyListener();
        saveData.setOnClickListener(listener);
        restoreData.setOnClickListener(listener);

//        saveData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
//                editor.putString("name", "Tom");
//                editor.putInt("age", 28);
//                editor.putBoolean("married", false);
//                editor.apply();
//            }
//        });
//
//        restoreData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
//                String name = pref.getString("name", "");
//                int age = pref.getInt("age", 0);
//                boolean married = pref.getBoolean("married", false);
//                Log.d("MainActivity", "name is " + name);
//                Log.d("MainActivity", "age is " + age);
//                Log.d("MainActivity", "married is " + married);
//            }
//        });
//
//    }

//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id){
//            case R.id.save_data:
//                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
//                editor.putString("name", "Tom");
//                editor.putInt("age", 28);
//                editor.putBoolean("married", false);
//                editor.apply();
//                break;
//            case R.id.restore_data:
//                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
//                String name = pref.getString("name", "");
//                int age = pref.getInt("age", 0);
//                boolean married = pref.getBoolean("married", false);
//                Log.d("MainActivity", "name is " + name);
//                Log.d("MainActivity", "age is " + age);
//                Log.d("MainActivity", "married is " + married);
//                break;
//            default:
//        }
}

       public class MyListener implements View.OnClickListener {

            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.save_data:
                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        editor.putString("name", "Tom");
                        editor.putInt("age", 28);
                        editor.putBoolean("married", false);
                        editor.apply();
                        break;
                    case R.id.restore_data:
                        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                        String name = pref.getString("name", "");
                        int age = pref.getInt("age", 0);
                        boolean married = pref.getBoolean("married", false);
                        Log.d("MainActivity", "name is " + name);
                        Log.d("MainActivity", "age is " + age);
                        Log.d("MainActivity", "married is " + married);
                        break;
                    default:
                }
            }
        }

}