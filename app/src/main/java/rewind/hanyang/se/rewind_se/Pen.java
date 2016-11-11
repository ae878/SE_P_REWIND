package rewind.hanyang.se.rewind_se;

import android.graphics.Paint;

/**
 * Created by ywon on 2016-11-05.
 */

public class Pen extends Paint {

    public Pen(int color){
        this.setColor(color);
        this.setStyle(Style.STROKE);
        this.setStrokeWidth((float)20.0);
       // BlurMaskFilter blur = new BlurMaskFilter(100,BlurMaskFilter.Blur.NORMAL);
        //this.setMaskFilter(blur);

      //  CornerPathEffect cE = new CornerPathEffect(50.0f);
       // this.setPathEffect(cE);
        this.setAntiAlias(true);

    //    this.setStrokeCap(Cap.ROUND);

        //this.setDither(true);
        this.setAlpha(20);
       // this.setStrokeJoin(Join.ROUND);
       // this.setStrokeCap(Cap.ROUND);
    }

    public void setPenColor( int color){
        this.setColor(color);
    }
    public int getPenColor(){
        return this.getColor();
    }
}
