package com.example.day01;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIv;
    /**
     * 跳过
     */
    private Button mBt;
    private TextView mTv;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);

        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.myanim);
        mIv.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);

        timer = new CountDownTimer(4000, 500) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTv.setText("倒计时:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                timer.cancel();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                finish();
                break;
        }
    }
}
