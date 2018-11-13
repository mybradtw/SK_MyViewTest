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
    private LinkedList<LinkedList<HashMap<String,Float>>> lines, recycler;
    private int lineWidth;


    public MyView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);

        lines = new LinkedList<>();
        recycler = new LinkedList<>();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        for (LinkedList<HashMap<String,Float>> line :lines){
            for (int i=1; i<line.size(); i++) {
                if (i == 1){
                    HashMap<String,Float> attr = line.get(i-1);
                    paint.setStrokeWidth(attr.get("width"));
                }else {
                    HashMap<String, Float> p0 = line.get(i - 1);
                    HashMap<String, Float> p1 = line.get(i);
                    canvas.drawLine(p0.get("x"), p0.get("y"),
                            p1.get("x"), p1.get("y"), paint);
                }
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            addNewLine();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE ||
                event.getAction() == MotionEvent.ACTION_DOWN){
            addNewPoint(ex, ey);
        }

        invalidate();   // repaint => onDraw()

        return true; //super.onTouchEvent(event);
    }

    private void addNewLine(){
        LinkedList<HashMap<String,Float>> line = new LinkedList<>();

        HashMap<String,Float> data = new HashMap<>();
        data.put("width", (float)lineWidth);
        line.add(data);

        lines.add(line);
        recycler.clear();
    }

    private void addNewPoint(float x, float y){
        HashMap<String, Float> point = new HashMap<>();
        point.put("x", x);
        point.put("y", y);
        lines.getLast().add(point);
    }

    public void clear(){
        lines.clear();
        invalidate();
    }

    public void undo(){
        if (lines.size()>0) {
            recycler.add(lines.removeLast());
            invalidate();
        }
    }
    public void redo(){
        if (recycler.size()>0) {
            lines.add(recycler.removeLast());
            invalidate();
        }
    }

    public void chLineWidth(int newWidth){
        lineWidth = newWidth;
        //invalidate();
    }


}
