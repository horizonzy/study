package com.example.zhaoyan.notebook;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private TextView tv1,tv2,tv3,tv4;
    private FragmentPagerAdapter mAdaper;
    private int mScreentWidth1_4;
    private ImageView tabLine;
    private long exitTime=0;
    private List<Fragment> mTabs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitActivity.getInstance().addActivity(this);
        initView();
        initEvevt();
        initTabline();
        mViewPager.setAdapter(mAdaper);
    }

    private void initEvevt() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }

    private void initTabline() {
        WindowManager windowManager= (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        mScreentWidth1_4=outMetrics.widthPixels/4;
        tabLine= (ImageView) findViewById(R.id.tabline);
        ViewGroup.LayoutParams lp=tabLine.getLayoutParams();
        lp.width=mScreentWidth1_4;
        tabLine.setLayoutParams(lp);
    }


    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);
        tv4= (TextView) findViewById(R.id.tv4);
        final FirstFragment tab1=new FirstFragment();
        SecondFragment tab2=new SecondFragment();
        ThirdFragment tab3=new ThirdFragment();
        ForthFragment tab4=new ForthFragment();
        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);
        mTabs.add(tab4);
        mAdaper=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lp.leftMargin= (int) (mScreentWidth1_4*position+mScreentWidth1_4*positionOffset);
                tabLine.setLayoutParams(lp);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
                resetColor();
                mViewPager.setCurrentItem(0,false);
                tv1.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lp.leftMargin=mScreentWidth1_4*0;
                tabLine.setLayoutParams(lp);
                break;
            case R.id.tv2:
                resetColor();
                mViewPager.setCurrentItem(1,false);
                tv2.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lp1= (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lp1.leftMargin=mScreentWidth1_4*1;
                tabLine.setLayoutParams(lp1);
                break;
            case R.id.tv3:
                resetColor();
                mViewPager.setCurrentItem(2,false);
                tv3.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lp2= (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lp2.leftMargin=mScreentWidth1_4*2;
                tabLine.setLayoutParams(lp2);
                break;
            case R.id.tv4:
                resetColor();
                mViewPager.setCurrentItem(3,false);
                tv4.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams lp3= (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lp3.leftMargin=mScreentWidth1_4*3;
                tabLine.setLayoutParams(lp3);
                break;
        }
    }
    private void resetColor(){
        tv1.setTextColor(0xff9e9999);
        tv2.setTextColor(0xff9e9999);
        tv3.setTextColor(0xff9e9999);
        tv4.setTextColor(0xff9e9999);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ExitActivity.getInstance().exit();
        }
    }
}
