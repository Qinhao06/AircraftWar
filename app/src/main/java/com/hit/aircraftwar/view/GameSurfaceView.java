package com.hit.aircraftwar.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.hit.aircraftwar.R;
import com.hit.aircraftwar.application.ImageManager;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    int count = 0;
    public float x = 50, y = 50;
    int backGroundTop = 0;
    int screenWidth = 480, screenHeight = 800;
    boolean mbLoop = false; //控制绘画线程的标志位
    private final SurfaceHolder mSurfaceHolder;
    private final Paint mPaint;
    public GameSurfaceView(Context context) {
        super(context);
        mbLoop = true;
        mPaint = new Paint();  //设置画笔
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
    }

    public void draw(){
        //通过SurfaceHolder对象的lockCanvans()方法，我们可以获取当前的Canvas绘图对象
        loading_img();
        //绘图的画布
        Canvas canvas = mSurfaceHolder.lockCanvas();
        if(mSurfaceHolder == null || canvas == null){
            return;
        }

        //绘制背景，图片滚动
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop-screenHeight, mPaint);
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, mPaint);
        backGroundTop += 1;
        if(backGroundTop == screenHeight)
            this.backGroundTop = 0;

        //通过unlockCanvasAndPost(mCanvas)方法对画布内容进行提交
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void loading_img(){
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        ImageManager.MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.mob);
        ImageManager.ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.elite);
        ImageManager.BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.boss);
        ImageManager.PROP_BLOOD_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_blood);
        ImageManager.PROP_BOMB_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bomb);
        ImageManager.PROP_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bullet);
        ImageManager.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_hero);
        ImageManager.ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_enemy);
    }

    @Override
    public void run() {
        //设置一个循环来绘制，通过标志位来控制开启绘制还是停止
        while (mbLoop){
            synchronized (mSurfaceHolder){
                draw();
            }
            try {
                Thread.sleep(200);
            }catch (Exception e){}
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        new Thread(this).start();
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        mbLoop = false;
    }
}
