package com.example.exerciciopinguim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

public class PinguimView extends View {
    private Bitmap mPenguin;
    private int mPHwidth;
    private int mPHheight;
    private float x;
    private float y;
    private float vx=1;
    private float vy=1;

    public PinguimView(Context cx) {
        super(cx);
        final Context viewContext = cx;
        mPenguin = BitmapFactory.decodeResource(getResources(),
                R.drawable.rain_penguin_180); //arquivo .png do pinguim
        //calculamos as coordenadas do centro do pinguim:
        mPHwidth = mPenguin.getWidth() / 2;
        mPHheight = mPenguin.getHeight() / 2;
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //pegamos as coordenadas onde foi tocada a tela:
                    x= event.getX();
                    y=event.getY();
                    vx=0;
                    vy=0;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xffff9900); //tela com cor de fundo laranja
        float angle = SystemClock.uptimeMillis() / 10.0f ; //retorna os milissegundos transcorridos a partir do boot
        canvas.translate(x, y); //trasladamos o pinguim
        canvas.rotate(angle, mPHwidth, mPHheight); //rotamos o pinguim
        canvas.drawBitmap(mPenguin, 0, 0, null);
        if (y+2*mPHheight + vy+1>=this.getHeight()){
            //se o pinguim chegou na parte inferior do view, então calculamos o valor vy do rebote:
            vy = -0.8f*vy;
        }
        else{
            //senão, o pinguim desce:
            vy = vy+1;
        }
        x = x + vx;
        y = y + vy;
        postInvalidateDelayed(20);
        //provoca chamar o método onDraw a cada 20 milissegundos
    }
}
