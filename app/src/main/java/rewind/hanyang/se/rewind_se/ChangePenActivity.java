package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class ChangePenActivity extends Activity {

    final int PEN_BTN_SIZE=10;

    int selectPen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button temp;
        LinearLayout.LayoutParams params;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams  layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags  = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount  = 0.7f;
        getWindow().setAttributes(layoutParams);
        getWindow().getAttributes().width   = (int)(MainActivity.SCREEN_WIDTH * 0.9);
        getWindow().getAttributes().height  = (int)(MainActivity.SCREEN_WIDTH * 0.8);

        selectPen=DrawActivity.selectPen.getType();

        setContentView(R.layout.popup_changepen);

        temp = (Button)findViewById(R.id.pencil);
        params = (LinearLayout.LayoutParams) temp.getLayoutParams();
        params.width = MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;
        params.height=MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;

        temp = (Button)findViewById(R.id.eraser);
        params = (LinearLayout.LayoutParams) temp.getLayoutParams();
        params.width = MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;
        params.height=MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;

        temp = (Button)findViewById(R.id.highlighter);
        params = (LinearLayout.LayoutParams) temp.getLayoutParams();
        params.width = MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;
        params.height=MainActivity.SCREEN_WIDTH/PEN_BTN_SIZE;


    }
    public void onClickPencil(View v){
        DrawActivity.selectPen.setType(Pen.PENCIL);
        initButton();
        Button temp = (Button)findViewById(R.id.pencil);
        temp.setAlpha((float)0.5);
        this.selectPen = Pen.PENCIL;
    }
    public void onClickHighlighter(View v) {
        DrawActivity.selectPen.setType(Pen.HIGHLIGHTER);
        initButton();
        Button temp = (Button)findViewById(R.id.highlighter);
        temp.setAlpha((float)0.5);
        this.selectPen = Pen.HIGHLIGHTER;
    }
    public void onClickEraser(View v){
        DrawActivity.selectPen.setType(Pen.ERASER);
        initButton();
        Button temp = (Button)findViewById(R.id.eraser);
        temp.setAlpha((float)0.5);
        this.selectPen = Pen.ERASER;

    }
    public void onClickBack(View v){
        Intent intent = getIntent();
        intent.putExtra("PEN_TYPE",selectPen);
        setResult(RESULT_OK,intent);
        this.finish();
    }

    private void initButton(){
        Button temp;
        temp = (Button)findViewById(R.id.pencil);
        temp.setAlpha((float)1.0);
        temp = (Button)findViewById(R.id.highlighter);
        temp.setAlpha((float)1.0);
        temp = (Button)findViewById(R.id.eraser);
        temp.setAlpha((float)1.0);
    }
}
