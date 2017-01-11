package com.dotsenko.tablefootball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {
    Game game;
    Ball ball;
    Field field;
    int startX=0;
    int startY=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gv=new GameView(this);
        gv.setOnTouchListener(this);
        field=new Field();
        setContentView(gv);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //int x=(int)event.getX();
        //int y=(int)event.getY();
        //ball.setNewBallPosition(x,y,1);
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            //ball.setNewBallPosition(x,y,1);
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            int currentX=(int)event.getX();
            int currentY=(int)event.getY();
            if(startX==0){
                startX=currentX;
            }
            if(startY==0){
                startY=currentY;
            }
            int valueX=field.getX()-(startX-currentX);
            field.setX(valueX);
            int valueY=field.getY()-(startY-currentY);
            field.setY(valueY);
            startX=(int)event.getX();
            startY=(int)event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            startX=0;
            startY=0;
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
            game=new Game();
            ball=new Ball();
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
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


        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;
            private Bitmap field_image;

            public DrawThread(SurfaceHolder surfaceHolder) {

                this.surfaceHolder = surfaceHolder;
                field_image = BitmapFactory.decodeResource(getResources(),R.drawable.field);
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
                        canvas.drawBitmap(field_image,field.getX(),field.getY(),paint);
                        //ball.drawBallPositions(canvas,paint);
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
