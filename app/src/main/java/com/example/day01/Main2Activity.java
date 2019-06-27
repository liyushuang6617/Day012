package com.example.day01;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 跳过
     */
    private Button mBt;
    private ImageView mIv;
    private TextView mTv;

    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator animator4;
    private ObjectAnimator animator5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);

        animator1 = ObjectAnimator.ofFloat(mIv, "alpha", 1f, 0f, 1f);
        animator2 = ObjectAnimator.ofFloat(mIv, "rotation", 0f, 360f, 0f);
        animator3 = ObjectAnimator.ofFloat(mIv, "scaleY", 1f, 0f, 1f);
        animator4 = ObjectAnimator.ofFloat(mIv, "scaleX", 1f, 0f, 1f);
        animator5 = ObjectAnimator.ofFloat(mIv, "translationY", 0, -300, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator5).with(animator4).with(animator3).with(animator2).with(animator1);
        animatorSet.setDuration(3000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                startActivity(new Intent(this, Main3Activity.class));
                finish();
                break;
            case R.id.iv:
                break;
        }
    }
}
