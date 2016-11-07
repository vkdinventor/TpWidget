package com.example.vikash.tpwidget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Window w = this.getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        Log.v("TAG","onCreate CAlled");

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        lp.gravity = Gravity.CENTER;
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_launcher);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something here
            }
        });

        // View view= LayoutInflater.from(context).inflate(R.layout.activity_main,null,false);
        wm.addView(image, lp);
    }
}
