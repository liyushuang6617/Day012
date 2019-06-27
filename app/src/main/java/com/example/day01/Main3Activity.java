package com.example.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.day01.adapter.Vp1Adapter;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private ViewPager mVp;
    private ArrayList<View> list;
    private Vp1Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);

        View view1 = View.inflate(this, R.layout.vp_item1, null);
        View view2 = View.inflate(this, R.layout.vp_item2, null);
        View view3 = View.inflate(this, R.layout.vp_item3, null);
        Button bt = view3.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, Main4Activity.class));
                finish();
            }
        });
        list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);

        adapter = new Vp1Adapter(this, list);
        mVp.setAdapter(adapter);
    }
}
