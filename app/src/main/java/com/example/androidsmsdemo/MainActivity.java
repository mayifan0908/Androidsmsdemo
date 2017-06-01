package com.example.androidsmsdemo;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;


public class MainActivity extends Activity {
    private String phoneNumber = "";
   private TextView tvphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvphone= (TextView) findViewById(R.id.phone);
        SMSSDK.initSDK(this, "175ba9bb5cc60",
                "cb8709bc9ed832031dc11cfc84f1468a");

    }

    public void register(View view) {
        // 打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    phoneNumber = phone;

                    Log.e("PhoneNumber", phone);
                    tvphone.setText(phoneNumber);
                    // 提交用户信息（此方法可以不调用）
                    // registerUser(country, phone);

                }
            }
        });
        registerPage.show(this);


        //注册成功以后跳转到我的页面MyActivity，并且在MyActivity显示注册的手机号码

    }

    public void friends(View view) {
        // 打开通信录好友列表页面
        ContactsPage contactsPage = new ContactsPage();
        contactsPage.show(this);
    }

}
