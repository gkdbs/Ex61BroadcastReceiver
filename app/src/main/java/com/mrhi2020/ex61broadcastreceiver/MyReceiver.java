package com.mrhi2020.ex61broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(  intent.getAction().equals("bbb") ){
            // 이 리시버가 방송을 수신하면 자동으로 발동하는 메소드
            Toast.makeText(context, "bbb received!!", Toast.LENGTH_SHORT).show();
        }

    }
}
