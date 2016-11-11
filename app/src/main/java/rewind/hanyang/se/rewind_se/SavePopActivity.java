package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class SavePopActivity extends Activity {
    public SavePopActivity(){};
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
    }
    public void onClickOk(View v){
        Intent intent = getIntent();
        editText = (EditText)findViewById(R.id.popupEdit);
        intent.putExtra("file_name",editText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
    public void onClickCancel(View v){
        this.finish();
    }
}