package com.example.flightgearandroidapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.text.AttributedCharacterIterator;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private float centerX;
    private float centerY;
    private float baseRadius;
    private JoystickListener joystickCallBack;

    public Joystick(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener){
            joystickCallBack = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet a, int style){
        super(context, a, style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener){
            joystickCallBack = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet a){
        super(context, a);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener){
            joystickCallBack = (JoystickListener) context;
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        setUpDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //getHolder().addCallback(this);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        getHolder().addCallback(this);
    }

    public void drawJoystick(float newX, float newY) {
        if (getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawColor(getResources().getColor(android.R.color.transparent), PorterDuff.Mode.CLEAR);
            myCanvas.drawRGB(93,184,226);
            colors.setARGB(255, 0, 0, 102);
            myCanvas.drawCircle(newX, newY, baseRadius, colors);
            getHolder().unlockCanvasAndPost(myCanvas);
        }
    }

    private void setUpDimensions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = 150;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.equals(this)) {
            if (motionEvent.getAction() != motionEvent.ACTION_UP) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (x + baseRadius > getWidth()){
                    x = getWidth() - baseRadius;
                } else if(x - baseRadius/2 < 71){
                    x = 71 + baseRadius/2;
                }
                if (y + baseRadius > getHeight()){
                    y = getHeight() - baseRadius;
                } else if(y - baseRadius/2 < 284){
                    y = 284 + baseRadius/2;
                }
                drawJoystick(x, y);
                if (x < centerX){
                    x*=-1;
                }
                if (y < centerY){
                    y*=-1;
                }
                joystickCallBack.onJoystickMoved(x / getWidth(), y/ getHeight());
            }
            else {
                drawJoystick(centerX, centerY);
                joystickCallBack.onJoystickMoved(0, 0);
            }

        }
        return true;
    }

    public interface JoystickListener {
        public void onJoystickMoved(float xPercent, float yPercent);
    }
}
