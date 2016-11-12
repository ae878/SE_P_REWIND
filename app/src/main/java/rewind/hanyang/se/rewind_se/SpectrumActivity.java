package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mijin on 2016-11-12.
 */

public class SpectrumActivity extends Activity {

    ImageView spectrumImage; // 스펙트럼 터치 좌표를 얻기 위한 변수

    float spectrumX,spectrumY; // 스펙트럼 좌표 얻어오는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams  layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags  = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount  = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.popup_spectrum);
        getWindow().getAttributes().width   = (int)(MainActivity.SCREEN_WIDTH * 0.9);
        getWindow().getAttributes().height  = (int)(MainActivity.SCREEN_WIDTH * 0.8);
        setContentView(R.layout.popup_spectrum);

        spectrumImage = (ImageView)findViewById(R.id.spectrumImage);


        System.out.println("HHHHH"+spectrumImage.getTop()+spectrumImage.getRight()) ;

        spectrumImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                final Bitmap mBitmap = ((BitmapDrawable)((ImageView)v).getDrawable()).getBitmap();
                int mPixel = mBitmap.getPixel((int)event.getY(), (int)event.getY());

                System.out.println(("QQ    "+event.getX()+"  "+ event.getY()));

                int A = Color.alpha(mPixel);
                int R = Color.red(mPixel);
                int G = Color.blue(mPixel);
                int B = Color.green(mPixel);

                DrawActivity.selectColor=Color.argb(255,R,G,B);
                return false;
            }
        });
    }
}

