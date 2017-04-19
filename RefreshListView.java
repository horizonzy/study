package com.example.zhaoyan.asyntask;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.icu.text.SimpleDateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by zhaoyan on 2017/3/30.
 */

public class RefreshListView  extends ListView implements AbsListView.OnScrollListener{
    private int firstVisibleItem;
    private boolean isRemark;
    private View header;
    private int headerHeight;
    private int startY;
    private int state;
    private int scrollState;
    private final int NONE=0;
    private final int PULL=1;
    private final int RELEASE=2;
    private final int REFLASHING=3;
    private IReflashListener mListener;
    public RefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        header=inflater.inflate(R.layout.header_layout,null);
        header.measure(0,0);
        headerHeight=header.getMeasuredHeight();
        topPadding(-headerHeight);
        this.addHeaderView(header);
    }
    private void topPadding(int topPadding){
        header.setPadding(header.getPaddingLeft(),topPadding,header.getPaddingRight(),header.getPaddingBottom());
        header.invalidate();
    }
    private void measureView(View v){
        ViewGroup.LayoutParams p=v.getLayoutParams();
        if(p==null){
            p=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width=ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight=p.height;
        if(tempHeight>0){
            height=MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }else {
            height=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        v.measure(width,height);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState=scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem=firstVisibleItem;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(firstVisibleItem==0){
                    isRemark=true;
                    startY= (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if(state==RELEASE){
                    state=REFLASHING;
                    reflashViewByState();
                    mListener.onReflash();
                }else if(state==PULL){
                    state=NONE;
                    isRemark=false;
                    reflashViewByState();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void onMove(MotionEvent ev) {
        if(!isRemark){
            return;
        }
        int endY= (int) ev.getY();
        int space=endY-startY;
        int topPadding=space-headerHeight;
        switch (state){
            case NONE:
                if(space>0){
                    state=PULL;
                    reflashViewByState();
                }
                break;
            case PULL:
                topPadding(topPadding);
                if(space>headerHeight+30&&scrollState==SCROLL_STATE_TOUCH_SCROLL){
                    state=RELEASE;
                    reflashViewByState();
                }
                break;
            case RELEASE:
                topPadding(topPadding);
                if(space<headerHeight+30){
                    state=PULL;
                    reflashViewByState();
                }else if(space<=0){
                    state=NONE;
                    isRemark=false;
                    reflashViewByState();
                }
                break;
            case REFLASHING:
                reflashComplete();
                break;
        }

    }
    private void reflashViewByState(){
        RotateAnimation animation=new RotateAnimation(0,180,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        RotateAnimation animation1=new RotateAnimation(180,0,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        animation1.setDuration(500);
        animation1.setFillAfter(true);
        TextView tip= (TextView) header.findViewById(R.id.tip);
        ImageView arrow= (ImageView) header.findViewById(R.id.arrow);
        ProgressBar progressBar= (ProgressBar) header.findViewById(R.id.progress);

        switch (state){
            case NONE:
                arrow.clearAnimation();
                topPadding(-headerHeight);
                break;
            case PULL:
                arrow.setVisibility(VISIBLE);
                progressBar.setVisibility(View.GONE);
                tip.setText("下拉可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(animation1);
                break;
            case RELEASE:
                arrow.setVisibility(VISIBLE);
                progressBar.setVisibility(View.GONE);
                tip.setText("松开可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(animation);
                break;
            case REFLASHING:
                topPadding(headerHeight+30);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                tip.setText("正在刷新");
                break;
        }
    }
    public void reflashComplete(){
        state=NONE;
        isRemark=false;
        reflashViewByState();
        TextView lastupdatetime= (TextView) header.findViewById(R.id.lastUpdateTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        String time=format.format(date);
        lastupdatetime.setText(time);
    }
    public void setInterface(IReflashListener iReflashListener){
        mListener=iReflashListener;
    }

    public interface IReflashListener{
        public void onReflash();
    }
}
