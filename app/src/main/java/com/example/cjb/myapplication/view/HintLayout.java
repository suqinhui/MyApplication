package com.example.cjb.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cjb.myapplication.R;
import com.example.cjb.myapplication.activity.MainActivity;

public class HintLayout extends LinearLayout {
    public HintLayout(final Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);// 水平排列
        //设置宽高
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(context).inflate(R.layout.layout_window_hint, null);
        Button btnContinue = (Button) view.findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭悬浮窗并跳转到主界面
                MainActivity.closeDesk(context);
                Intent intentToMain = new Intent(context, MainActivity.class);
                intentToMain.addCategory(Intent.CATEGORY_LAUNCHER);
                intentToMain.setAction(Intent.ACTION_MAIN);
                intentToMain.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentToMain);
            }
        });
        this.addView(view);
    }
}
