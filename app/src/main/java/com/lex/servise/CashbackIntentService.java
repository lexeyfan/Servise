package com.lex.servise;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import android.os.Handler;

import java.util.Date;

public class CashbackIntentService extends IntentService{
    final static String CASHBACK_INFO = "info";
    int i;
    public static Context ctx;
    public CashbackIntentService() {
        super("IntentService");
        ctx = this;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String cb_category = intent.getStringExtra("service");

        String cbinfo = getCashbackInfo(cb_category);
        sendCashbackInfoToClient(cbinfo);
    }
    private String getCashbackInfo(String cbcat){
        String cashback;
        String tt = cbcat;
        i=1;
        while("1".equals(tt)){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if( i==10) {
                i=1;
                Handler mainHandler = new Handler(getMainLooper());

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Do your stuff here related to UI, e.g. show toast
                        String txt =String.valueOf(i);
                        Date date = new Date();
                        String txt2 =String.valueOf(date);
                        Toast.makeText(getApplicationContext(), txt + ' ' +  txt2 , Toast.LENGTH_LONG).show();
                    }
                });

        }
        }
        Handler mainHandler = new Handler(getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                // Do your stuff here related to UI, e.g. show toast
                String txt =String.valueOf(i);
                Date date = new Date();
                String txt2 =String.valueOf(date);
                Toast.makeText(getApplicationContext(), "STOP" , Toast.LENGTH_LONG).show();
            }
        });
        cashback = "Exit";
        return cashback;

        //if("electronics".equals(cbcat)){
      //      cashback = "Upto 20% cashback on electronics";
       // }else if("fashion".equals(cbcat)){
        //    cashback = "Upto 60% cashbak on all fashion items";
       // }else{
       //     cashback = "All other categories except fashion and electronics, flat 30% cashback";
      //  }
       // return cashback;
    }
    private void sendCashbackInfoToClient(String msg){
        Intent intent = new Intent();
        intent.setAction(CASHBACK_INFO);
        intent.putExtra("service",msg);
        sendBroadcast(intent);
    }
}