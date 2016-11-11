package rewind.hanyang.se.rewind_se;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by ywon on 2016-10-09.
 */
public class DrawPoint {
    final int MAX_POINT = 100;

    ArrayList<TouchPoint> arrayTouchPoint;

    int color= Color.BLACK;

    public DrawPoint(){
        arrayTouchPoint = new ArrayList<TouchPoint>();
    }

    public class TouchPoint{
        float x;
        float y;
        boolean draw;
        Pen pen;
        public TouchPoint(float x, float y, int selectColor){
            this.x = x;
            this.y = y;
            this.pen = new Pen( selectColor);
        }
    }

    public float getX(int index){
        return arrayTouchPoint.get(index).x;
    }
    public float getY(int index){
        return arrayTouchPoint.get(index).y;
    }
    public Paint getPaint(int index){ return arrayTouchPoint.get(index).pen;}
    public boolean isFull(){ return arrayTouchPoint.size()>=MAX_POINT;}
    public void pointClear(){arrayTouchPoint.clear();}


    public void addPoint(float x, float y, boolean draw , int selectColor){
        arrayTouchPoint.add(new TouchPoint(x,y,selectColor));
        arrayTouchPoint.get(arrayTouchPoint.size()-1).draw = draw;
    }
}
