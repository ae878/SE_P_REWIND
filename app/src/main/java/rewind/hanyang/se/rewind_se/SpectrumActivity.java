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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mijin on 2016-11-12.
 */

public class SpectrumActivity extends Activity {

    ImageView spectrumImage; // 스펙트럼 터치 좌표를 얻기 위한 변수
    ImageView showColor ; // 스펙트럼에서 선택한 색 보여줌

    Button valueUp;
    Button valueDown;

    float hsv[] = new float[3];
    int colorValue=0;

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
        showColor = (ImageView)findViewById(R.id.showcolor);
        valueDown = (Button)findViewById(R.id.valueDown);
        valueUp= (Button)findViewById(R.id.valueUp);


        spectrumImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Bitmap bitmap = ((BitmapDrawable)((ImageView)v).getDrawable()).getBitmap();
                Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap,(spectrumImage.getWidth()),(spectrumImage.getHeight()),true); //비트맵 크기 조절

                int mPixel = mBitmap.getPixel((int)(event.getX()), (int)(event.getY()));

                int R = (mPixel & 0xff0000) >> 16;
                int G = (mPixel & 0x00ff00) >> 8;
                int B = (mPixel & 0x0000ff) >> 0;

                DrawActivity.selectPen.setColor(Color.argb(255,R,G,B)); // 펜 색 설정
                showColor.setBackgroundColor(Color.argb(255,R,G,B)); //설정한 색 보여주기

                Color.RGBToHSV(R,G,B,hsv);
                return false;
            }
        });

    }
    public void onClickValueUP(View v){
       
        hsv[2] = hsv[2]+(float)0.01;

        DrawActivity.selectPen.setColor(Color.HSVToColor(hsv)); // 펜 색 설정
        showColor.setBackgroundColor( Color.HSVToColor(hsv)); //설정한 색 보여주기
    }

    public void onClickValueDown(View v){
        hsv[2] = hsv[2]-(float)0.01;

        DrawActivity.selectPen.setColor(Color.HSVToColor(hsv)); // 펜 색 설정
        showColor.setBackgroundColor(Color.HSVToColor(hsv)); //설정한 색 보여주기
    }

}

