package rewind.hanyang.se.rewind_se;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

/**
 * Created by Youngmin on 2016-11-15.
 *
 */

public class JoinActivity extends Activity {
    private EditText edit_email;
    private EditText edit_password;
    private Button join_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_join);

        init();


    }
    public void init(){
        edit_email = (EditText)findViewById(R.id.e_mail_edit);
        edit_password=(EditText)findViewById(R.id.password_edit_text);

        join_btn =(Button)findViewById(R.id.join_btn);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String password = edit_password.getText().toString();

                process(email,password);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void process(String email, String password){
        class InsertData extends AsyncTask<String,Void,String>{

            @Override
            protected String doInBackground(String... params) {
                try{
                    String email = (String)params[0];
                    String password = (String)params[1];
                    String data = "email="+email+"&passwd="+password;
                    String urlPath = "http://115.68.95.143/public_html/regis.php?" +data;
                    URL url = new URL(urlPath);

                    HttpURLConnection con = (HttpURLConnection)url.openConnection();

                    Log.d("Youngmin@@@@@@@@@@","Complete");

                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setRequestMethod("REQUEST");

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = rd.readLine())!=null){
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch (Exception e) {
                    return new String("Exeption: " + e.getMessage());
                }

            }
        }
        Log.d("Youngmin@@@@@@@@@@","Execute!!!");
        InsertData task = new InsertData();
        task.execute(email,password);
    }
}
