package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

    static final int NEW_DRAWING = 0;
    static final int LOAD_DRAWING = 1;

    static String basicPath ;

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.init();
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    public void init() {
        basicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/REWIND";
        getScreenSize(this);
    }

    public void getScreenSize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point src = new Point();
        display.getSize(src);
        SCREEN_HEIGHT = src.y;
        SCREEN_WIDTH = src.x;
    }



    /*
    Start new drawing activity
    */
    public void onClickNew(View v){
        Intent intent = new Intent(getApplicationContext(),DrawActivity.class);
        intent.putExtra("status",NEW_DRAWING);
        startActivity(intent);
    }
    /*
    Start load image activity
     */
    public void onClickLoad(View v){
        Intent intent = new Intent(getApplicationContext(),DrawActivity.class);
        intent.putExtra("status",LOAD_DRAWING);
        startActivity(intent);
    }

}
