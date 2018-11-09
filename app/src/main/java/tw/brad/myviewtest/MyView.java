package tw.brad.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View  {
    private LinkedList<HashMap<String,Float>> line;


    public MyView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);

        line = new LinkedList<>();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        canvas.drawLine(0,0, 400, 400, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        HashMap<String, Float> point = new HashMap<>();
        point.put("x", ex);
        point.put("y", ey);
        line.add(point);


        return true; //super.onTouchEvent(event);
    }
}
