package com.example.ab3_exc2_laurarandl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(e -> {
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);

            Canvas canvas = surfaceView.getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);
            float x_maxSize = canvas.getWidth();
            float y_maxSize = canvas.getHeight()-10;
            int radius = 15;
            float circle_x = ThreadLocalRandom.current().nextInt(0+radius, (int)x_maxSize)-radius;
            float circle_y = ThreadLocalRandom.current().nextInt(0+radius, (int)y_maxSize-64-radius);
            canvas.drawCircle(circle_x, circle_y, radius, paint);
            surfaceView.getHolder().unlockCanvasAndPost(canvas);

            View.OnTouchListener touchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        float x_pos = motionEvent.getX();
                        float y_pos = motionEvent.getY();

                        float x_missed = Math.abs(circle_x-x_pos);
                        float y_missed = Math.abs(circle_y-y_pos);

                        double missed_pixels = Math.sqrt(Math.pow(x_missed, 2)+Math.pow(y_missed, 2));

                        Toast toast = Toast.makeText(getApplicationContext(), "Missed by " + missed_pixels + " Pixels", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    return true;
                }
            };
            surfaceView.setOnTouchListener(touchListener);
        });
    }
}