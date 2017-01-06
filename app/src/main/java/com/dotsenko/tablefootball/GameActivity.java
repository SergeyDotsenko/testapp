package com.dotsenko.tablefootball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {
    Game game;
    Ball ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gv=new GameView(this);
        gv.setOnTouchListener(this);
        setContentView(gv);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        ball.setNewBallPosition(x,y,1);
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("test", x + "/" + y);
            ball.setNewBallPosition(x,y,1);
        }
        return true;
    }

    class GameView extends SurfaceView implements SurfaceHolder.Callback{

        private DrawThread drawThread;

        public GameView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
            game=new Game();
            ball=new Ball();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }


        /*public boolean onTouch(View view, MotionEvent event) {
            int x=(int)event.getX();
            int y=(int)event.getY();
            //ball.setNewBallPosition(x,y,1);
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("test", x + "/" + y);
            }
            return true;
        }*/


        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                Paint paint=new Paint();
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        Bitmap field = BitmapFactory.decodeResource(getResources(),R.drawable.field);
                        canvas.drawBitmap(field,0,0,paint);
                        ball.drawBallPositions(canvas,paint);
                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
    }
}
