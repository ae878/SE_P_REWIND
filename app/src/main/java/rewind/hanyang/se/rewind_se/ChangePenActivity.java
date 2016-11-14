package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ChangePenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams  layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags  = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount  = 0.7f;
        getWindow().setAttributes(layoutParams);
        getWindow().getAttributes().width   = (int)(MainActivity.SCREEN_WIDTH * 0.9);
        getWindow().getAttributes().height  = (int)(MainActivity.SCREEN_WIDTH * 0.8);
        setContentView(R.layout.popup_changepen);
    }
    public void onClickPencil(View v){
        DrawActivity.selectPen.setType(Pen.PENCIL);
    }
    public void onClickHighlighter(View v){
        DrawActivity.selectPen.setType(Pen.HIGHLIGHTER);
    }
    public void onClickEraser(View v){
        DrawActivity.selectPen.setType(Pen.ERASER);
    }
}
