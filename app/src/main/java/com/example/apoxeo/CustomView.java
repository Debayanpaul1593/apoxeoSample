package com.example.apoxeo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import static com.example.apoxeo.R.color.mintGreen;
import static com.example.apoxeo.R.color.orange;

public class CustomView extends View {
    Paint paint;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initView(Canvas canvas){
        canvas.drawColor(getResources().getColor(mintGreen));
        paint = new Paint();
        paint.setColor(getResources().getColor(orange));
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, 450);
        path.lineTo(getWidth()/2, getWidth()/1.5f);
        path.lineTo(getWidth(), 450);
        path.lineTo(getWidth(), 0);
        path.lineTo(0, 0);
        path.close();

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initView(canvas);
    }
}
