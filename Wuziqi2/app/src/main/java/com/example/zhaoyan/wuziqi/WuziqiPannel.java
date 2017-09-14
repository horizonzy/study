package com.example.zhaoyan.wuziqi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyan on 2017/3/21.
 */

public class WuziqiPannel extends View {
    private int MAX_NUM=5;
    private int mPanelWidth;
    private int MAX_LINE=10;
    private float mLineHeight;
    private Paint paint;
    private float mScale=1.0f*3/4;
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private boolean isWhite=true;
    private boolean mGameOver;
    private boolean mWhiteWin;
    private List<Point> mWhiteArray=new ArrayList<>();
    private List<Point> mBlackArray=new ArrayList<>();

    public WuziqiPannel(Context context) {
        super(context);
        Log.i("AA", "1: ");
    }

    public WuziqiPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("AA", "2");
    }

    public WuziqiPannel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("AA", "3 ");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int width=widthSize;
        if(widthMode==MeasureSpec.UNSPECIFIED){
            width=heightSize;
        }else if(heightMode==MeasureSpec.UNSPECIFIED){
            width=width;
        }
        setMeasuredDimension(width,width);
        paint=new Paint();
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        mWhitePiece= BitmapFactory.decodeResource(getResources(),R.drawable.stone_w2);
        mBlackPiece=BitmapFactory.decodeResource(getResources(),R.drawable.stone_b1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth=w;
        mLineHeight=mPanelWidth*1.0f/MAX_LINE;
        int x= (int) (mLineHeight*mScale);
        int y= (int) (mLineHeight*mScale);
        mWhitePiece=Bitmap.createScaledBitmap(mWhitePiece,x,y,false);
        mBlackPiece=Bitmap.createScaledBitmap(mBlackPiece,x,y,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPiece(canvas);
        checkGanme();

    }

    private void checkGanme() {
        boolean whiteWin=checkFivePieceInline(mWhiteArray);
        boolean blackWin=checkFivePieceInline(mBlackArray);
        if(whiteWin){
            Toast.makeText(getContext(),"白棋胜利",Toast.LENGTH_SHORT).show();
            mGameOver=true;

        }
        if(blackWin){
            Toast.makeText(getContext(),"黑棋胜利",Toast.LENGTH_SHORT).show();
            mGameOver=true;
        }
    }
    public boolean checkFivePieceInline(List<Point> points){
        for(Point p:points){
            int x=p.x;
            int y=p.y;
            boolean win=checkHorizonInline(x,y,points);
            if(win) return true;
            win=checkVertical(x,y,points);
            if(win) return true;
            win=checkLeanRight(x,y,points);
            if(win) return true;
            win=checkLeanLeft(x,y,points);
            if(win) return true;
        }
        return false;
    }

    public boolean checkHorizonInline(int x,int y,List<Point> pieces){
        int count=1;
        for(int i=1;i<MAX_NUM;i++){
            if(pieces.contains(new Point(x+i,y))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        for(int i=1;i<MAX_NUM;i++){
            if(pieces.contains(new Point(x-i,y))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;

        return false;
    }
    public boolean checkVertical(int x,int y,List<Point> points){
        int count=1;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x,y+i))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x,y-i))){
                count++;
            }else {
                break;
            }
            if(count==MAX_NUM) return true;
        }
        return false;
    }
    public boolean checkLeanRight(int x,int y,List<Point> points){
         int count=1;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x+i,y-i))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x-i,y+i))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        return false;
    }
    public boolean checkLeanLeft(int x,int y,List<Point> points){
        int count=1;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x+i,y+i))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        for(int i=1;i<MAX_NUM;i++){
            if(points.contains(new Point(x-i,y-i))){
                count++;
            }else {
                break;
            }
        }
        if(count==MAX_NUM) return true;
        return false;
    }
    private void drawPiece(Canvas canvas) {
          for(int i=0,n=mWhiteArray.size();i<n;i++){
              Point point=mWhiteArray.get(i);
              int x=point.x;
              int y=point.y;
              canvas.drawBitmap(mWhitePiece,mLineHeight/8+x*mLineHeight,mLineHeight/8+y*mLineHeight,null);
          }
        for(int i=0,n=mBlackArray.size();i<n;i++){
            Point point=mBlackArray.get(i);
            int x=point.x;
            int y=point.y;
            canvas.drawBitmap(mBlackPiece,mLineHeight/8+x*mLineHeight,mLineHeight/8+y*mLineHeight,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mGameOver) return false;
        int action=event.getAction();
        if(action==MotionEvent.ACTION_UP){
            int x= (int) event.getX();
            int y= (int) event.getY();
            Point point=getValidPoint(x,y);
            if(mWhiteArray.contains(point)||mBlackArray.contains(point)){
                return false;
            }
            if(isWhite){
                mWhiteArray.add(point);
            }else {
                mBlackArray.add(point);
            }
            isWhite=!isWhite;
            invalidate();
            return true;
        }
        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int)(x/mLineHeight),(int)(y/mLineHeight));
    }

    private void drawBoard(Canvas canvas) {
        for(int i=0;i<MAX_LINE;i++){
            int startX= (int) (mLineHeight/2);
            int endX= (int) (mLineHeight/2+9*mLineHeight);
            int y= (int) (mLineHeight/2+i*mLineHeight);
            canvas.drawLine(startX,y,endX,y,paint);
            canvas.drawLine(y,startX,y,endX,paint);
        }
    }
    public void restart(){
        mGameOver=false;
        isWhite=true;
        mWhiteArray.clear();
        mBlackArray.clear();
        if(Looper.getMainLooper()==Looper.myLooper()){
            invalidate();
        }else {
            postInvalidate();
        }
    }

}
