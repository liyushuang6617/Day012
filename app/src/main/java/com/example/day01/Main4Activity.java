package com.example.day01;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.day01.adapter.Vp2Adapter;
import com.example.day01.fragment.Fragment1;
import com.example.day01.fragment.Fragment2;
import com.example.day01.fragment.Fragment3;
import com.example.day01.fragment.Fragment4;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> list;
    private ArrayList<String> title;
    private Vp2Adapter adapter;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);

        mToolbar.setTitle("主题");
        mToolbar.setSubtitle("副标题");
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.app_name, R.string.app_name);
//        mDl.addDrawerListener(toggle);
//        toggle.syncState();

        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        fragment4 = new Fragment4();
        list.add(fragment4);

        title = new ArrayList<>();
        title.add("A");
        title.add("B");
        title.add("C");
        title.add("D");

        adapter = new Vp2Adapter(getSupportFragmentManager(), list, title);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "A");
        menu.add(1, 2, 1, "B");
        menu.add(1, 3, 1, "C");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                fragment4.
                EventBus.getDefault().post(new LinearLayoutManager(this));
                break;
            case 2:
                EventBus.getDefault().post(new GridLayoutManager(this, 2));
                break;
            case 3:
//                EventBus.getDefault().post(new StaggeredGridLayoutManager(this,3));
                break;
        }
        return super.onContextItemSelected(item);
    }
}
