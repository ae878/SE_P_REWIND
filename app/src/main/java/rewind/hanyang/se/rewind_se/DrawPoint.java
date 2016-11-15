package rewind.hanyang.se.rewind_se;

import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by ywon on 2016-10-09.
 *
 */
class DrawPoint {
    ArrayList<TouchPoint> arrayTouchPoint;

    DrawPoint(){
        arrayTouchPoint = new ArrayList<>();
    }

    private class TouchPoint{
        float x;
        float y;
        boolean draw;
        Pen pen;
        private TouchPoint(float x, float y, Pen pen){
            this.x = x;
            this.y = y;
            this.pen = new Pen(pen);
        }
    }

    float getX(int index){
        return arrayTouchPoint.get(index).x;
    }
    float getY(int index){
        return arrayTouchPoint.get(index).y;
    }
    boolean isDraw(int index){ return arrayTouchPoint.get(index).draw;}
    Paint getPaint(int index){ return arrayTouchPoint.get(index).pen;}


    void addPoint(float x, float y, boolean draw , Pen pen){
        arrayTouchPoint.add(new TouchPoint(x,y,pen));
        arrayTouchPoint.get(arrayTouchPoint.size()-1).draw = draw;
    }
}
