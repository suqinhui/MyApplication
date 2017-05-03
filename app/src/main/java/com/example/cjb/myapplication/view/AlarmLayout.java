package com.example.cjb.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cjb.myapplication.R;

public class AlarmLayout extends LinearLayout {
    public AlarmLayout(final Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);// 水平排列
        //设置宽高
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(context).inflate(R.layout.layout_window_alarm, null);
        TextView tvClose = (TextView) view.findViewById(R.id.tv_close);
        tvClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送广播去关闭铃声，在AlarmBroadcastReceiver接收
                Intent intent = new Intent();
                intent.setAction("stop_alarm");
                context.sendBroadcast(intent);
            }
        });
        this.addView(view);
    }
}
