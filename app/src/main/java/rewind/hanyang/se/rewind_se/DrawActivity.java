package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by ywon on 2016-10-11.
   DrawActivity
   그림을 그리는 액티비티
 */
public class DrawActivity extends Activity {

    /* Intent ,, for result */
    final int SAVE_IMAGE = 0;
    final int LOAD_IMAGE = 1;
    final int PEN_CHANGE = 2;


    DrawPoint drawPoint; // 그려지는 점들의 데이터
    CanvasView mView; // 그림을 그릴 캔버스

    int status;

    static Pen selectPen;

    Bitmap loadImage = null;
    ImageView spectrumImage; // 스펙트럼 이미지뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        status = intent.getExtras().getInt("status");


        // 이미지를 불러와서 시작하는 경우
        if(status == MainActivity.LOAD_DRAWING){
            Intent Loadintent = new Intent(Intent.ACTION_GET_CONTENT);
            Loadintent.setType("image/*");
            startActivityForResult(Loadintent, LOAD_IMAGE);
        }

        //윈도우 FULL SCREEN 설정
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_draw);

        this.initVariables();

        FrameLayout frame = (FrameLayout) findViewById(R.id.drawLayout);
        frame.addView(mView,0);


        spectrumImage = (ImageView)findViewById(R.id.spectrumImage);
        if(spectrumImage!=null)
        System.out.println("hhh"+spectrumImage.getX()+spectrumImage.getY());



    }

    /*______ initVariavles ______
    *  16-10-11
    *  변수 초기화
    */
    private void initVariables(){
        mView = new CanvasView(this); // 그림을 그릴 뷰
        mView.setDrawingCacheEnabled(true); // view 가 변할때 마다 Cache로 저장
        drawPoint = new DrawPoint(); // 터치된 점을 관리 할 Class
        selectPen = new Pen(Color.BLACK, Pen.PENCIL);

        ImageButton pentypeButton = (ImageButton)findViewById(R.id.popupChangePen);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) pentypeButton.getLayoutParams();
        params.width = MainActivity.SCREEN_WIDTH/7;
        params.height=MainActivity.SCREEN_WIDTH/7;
        pentypeButton.setImageResource(R.drawable.pencil);


    }

    /*______ onTouchEvent ______
     * 16-10-11
     * 터치이벤트 처리
     * 터치된 좌표를 drawPoint 에 추가한다.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN: // 터치 가 시작되었을 때
                drawPoint.addPoint(event.getX(), event.getY(),false, selectPen);// 터치된 좌표를 drawPoint에 넘겨준다.
                mView.invalidate(); // view 를 다시 그린다.

            case MotionEvent.ACTION_MOVE: // 터치되고 움직일 때
                drawPoint.addPoint(event.getX(),event.getY(),true,selectPen);  // 터치된 좌표를 drawPoint에 넘겨준다.
                mView.invalidate(); // view 를 다시 그린다.

        }

        return true;
    }

    /*______ CanvasView ______
     * 16-10-10
     * Canvas
     */
    public  class CanvasView extends View {
        public CanvasView( Context context){
            super(context);

        }
        public void onDraw(Canvas canvas){
            drawCanvas(canvas);
            drawLoadImage(canvas);
        }

        public void drawCanvas(Canvas canvas){
                for (int i = 1; i < drawPoint.arrayTouchPoint.size(); i++)
                    if (drawPoint.isDraw(i)) {
                        canvas.drawLine(drawPoint.getX(i - 1), drawPoint.getY(i - 1)
                                , drawPoint.getX(i), drawPoint.getY(i), drawPoint.getPaint(i));
                    } else {
                        canvas.drawPoint(drawPoint.getX(i), drawPoint.getY(i), drawPoint.getPaint(i));
                    }
        }
        public void drawLoadImage(Canvas canvas){
            if(status == LOAD_IMAGE){
                canvas.drawBitmap(loadImage,0,0,null);
            }
        }

    }
    /*______ onClickSave ______
     * 16-10-11
     * .저장 버튼 클릭시 파일이름을 받는 액티비티를 실행시킨다.
     */
    public void onClickSave(View v){
        Intent intent = new Intent(DrawActivity.this,SavePopActivity.class);
        startActivityForResult(intent,SAVE_IMAGE);
    }

    /*______ onClickPopUp ______
   * 16-11-12
   * .스펙트럼 팝업창 띄우는 버튼
   */
    public void onClickPopUp(View v){
        startActivity(new Intent(this,SpectrumActivity.class));
    }

    /*______ onClickChangePen ______
   * 16-11-15
   *  펜의 종류를 편경하는 팝업
   */
    public void onClickChangePen(View v){
        Intent intent = new Intent(DrawActivity.this,ChangePenActivity.class);
        startActivityForResult(intent,PEN_CHANGE);

    }

    /*______ onActivityResult ______
        * 16-10-11
        *  파일이름을 받는 액티비티로부터 입력된 파일이름을 받아온다.
        */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SAVE_IMAGE:
                    //액티비티로 받아온 파일이름으로 저장을 시도한다.
                    if (saveView(data.getStringExtra("file_name")))
                        Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
                    break;

                case LOAD_IMAGE:
                    try {
                        InputStream stream = getContentResolver().openInputStream(data.getData());
                        loadImage = BitmapFactory.decodeStream(stream);
                    } catch (Exception e) {
                        return;
                    }
                break;
                case PEN_CHANGE:
                    Log.d("ddd","ddd");
                    ImageButton pentypeButton = (ImageButton)findViewById(R.id.popupChangePen);
                    switch(data.getIntExtra("PEN_TYPE",1)){
                        case Pen.PENCIL:
                            pentypeButton.setImageResource(R.drawable.pencil);
                            break;
                        case Pen.ERASER:
                            pentypeButton.setImageResource(R.drawable.eraser);
                            break;
                        case Pen.HIGHLIGHTER:
                            pentypeButton.setImageResource(R.drawable.highlighter);
                            break;
                    }
                    break;

            }
        }
    }

    /*______ saveView ______
     * 16-10-10
     * fileName을 지정된 디렉터리에 png파일로 저장한다.
     */
    public boolean saveView(String fileName) {

        Bitmap temp = mView.getDrawingCache();
        File dir = new File(MainActivity.basicPath);
        // 디렉터리가 존재하지 않다면 생성한다.
        if(!dir.exists()){dir.mkdir();}

        // 비트맵 이미지를 png 파일로 저장한다.
        try {
            FileOutputStream fos = new FileOutputStream(new File(MainActivity.basicPath, fileName+".png"));
            temp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            //실패시 false 반환
            return false;
        }
        //변화된 파일 상태를 갱신하도록 한다.
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse ("file://" + Environment.getExternalStorageDirectory())));
        return true;
    }




}
