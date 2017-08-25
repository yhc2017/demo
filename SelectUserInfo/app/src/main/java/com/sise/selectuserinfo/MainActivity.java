package com.sise.selectuserinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   public ImageButton imageButton,imageButton2;
   public ImageView imageView;
   public Button button;
   public TextView editText,editText2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageButton = (ImageButton) findViewById(R.id.touxiang);
            imageButton2 = (ImageButton) findViewById(R.id.erweimaImg);

            imageView = (ImageView) findViewById(R.id.sexImg);
            editText = (TextView) findViewById(R.id.nicengEditText);
            editText2 = (TextView) findViewById(R.id.useridEditText);

            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(this);

            sendRequestWithOkHttp("http://192.168.0.109:3306/queryUserInfo.php", new okhttp3.Callback(){
                /**
                 * Called when the request could not be executed due to cancellation, a connectivity problem or
                 * timeout. Because networks can fail during an exchange, it is possible that the remote server
                 * accepted the request before the failure.
                 *
                 * @param call
                 * @param e
                 */
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("MainActivity","出现异常");
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String responseData = response.body().string(); //得到返回具体内容

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                parseJSONWithGson(responseData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            });


    }


    //*********//
    private void sendRequestWithOkHttp(String address, okhttp3.Callback callback) {

        Intent intent = getIntent();
        String data = intent.getStringExtra("userinfo");
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();


        //*******
        OkHttpClient client = new OkHttpClient(); //创建 OkHttpClient实例
        RequestBody requestBody = new FormBody.Builder().add("userid", data).build();
        Request request = new Request.Builder().url("http://192.168.0.109/queryUserInfo.php").post(requestBody).build();
        client.newCall(request).enqueue(callback);
        //*******


    }

    private void parseJSONWithGson(String jsonData) throws JSONException {

        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>()
        {}.getType());
        for (App app : appList) {

            final String url1 = app.getImg();
            final String url2 = app.getSex();
            final String url3 = app.getErweima();
            final String necheng = app.getName();
            final  String wechatID = app.getWechat();

            Log.d("MainActivity", "img" + url1);
            Log.d("MainActivity", "sex" + url2);
            Log.d("MainActivity", "erweima" + url3);
            Log.d("MainActivity", "necheng" + necheng);
            Log.d("MainActivity", "userid" + wechatID);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    editText.setText(necheng);
                    editText2.setText(wechatID);

                    Glide.with(MainActivity.this).load(url2).into(imageView);
                    Glide.with(MainActivity.this).load(url1).into(imageButton);
                    Glide.with(MainActivity.this).load(url3).into(imageButton2);


                }
            });


        }

//********
    }
    //*********//



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button:
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                default:
                    break;
            }
    }


}
