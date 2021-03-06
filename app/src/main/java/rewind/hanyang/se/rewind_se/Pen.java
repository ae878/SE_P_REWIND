package rewind.hanyang.se.rewind_se;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by ywon on 2016-11-05.
 *
 */
class Pen extends Paint {
    static final int PENCIL = 0;
    static final int ERASER = 1;
    static final int HIGHLIGHTER = 2;

    private int type;
    private float width;

    Pen(int color , int type){
        this.setColor(color);
        this.setType(type);
        this.setStyle(Style.STROKE);
        this.width = (float)20.0;
        this.setStrokeWidth(width);
        this.setAntiAlias(true);
    }
    Pen(Pen pen){
        this.setType(pen.type);
        this.setColor(pen.getColor());
        this.setAlpha(pen.getAlpha());
        this.setStrokeWidth(pen.getStrokeWidth());
        this.setAntiAlias(true);
    }

    void setType(int type){
        this.type = type;
        switch(this.type){
            case PENCIL:
                this.setStyle(Style.STROKE);
                this.setStrokeWidth((float)20.0);
                this.setAntiAlias(true);
                this.setAlpha(255);
                break;
            case ERASER:
                this.setAlpha(255);
                this.setColor(Color.WHITE);
                break;
            case HIGHLIGHTER:
                this.setAlpha(30);
                break;
        }
    }
    int getType(){return this.type;}

}
