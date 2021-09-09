package org.techtown.example.expandablelistview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends Activity implements View.OnClickListener{
    private EditText et_pass, et_name, et_email, et_passconf;
    private Button btn_register, btn_pwdCheck;
    private TextView et_birth;
    private TextView checkPwd;
    private String responseData;
    private String pwd, pwdConf;
    private TextView txt_birth;


    private String userEmail, userPwd, userName, userBirth;
    TextView dateTXTLogin;
    DatePickerDialog.OnDateSetListener listener;



    private List<UserDTO> userList;

    private OKHttpConnection okHttpConnection;
    private JSONObject jObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);

        okHttpConnection = new OKHttpConnection();

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById( R.id.et_pass );
        et_passconf = findViewById(R.id.et_passconf);
        et_name = findViewById( R.id.et_name );
        btn_pwdCheck = findViewById(R.id.btn_pass_conf);
        btn_register = findViewById( R.id.btn_register );
        txt_birth = findViewById(R.id.txt_birth);
        checkPwd = findViewById(R.id.checkPwd);
        et_birth = findViewById(R.id.et_birth);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        Date mDate = new Date();
        SimpleDateFormat date_now =new SimpleDateFormat("yyyy-mm-dd");
        txt_birth.setText(date_now.format(mDate));

        txt_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = year+"-"+ month +"-"+dayOfMonth;

                txt_birth.setText(date);


            }
        };


        //회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(this);
        btn_pwdCheck.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_pass_conf){
            pwd = et_pass.getText().toString();
            pwdConf = et_passconf.getText().toString();

            if(!pwd.equals(pwdConf)) {
                checkPwd.setText("비밀번호가 다릅니다. 다시 입력해주세요 ");
                //Toast.makeText(RegisterActivity.this,"비밀번호가 다릅니다. 다시 입력해주세요 ",Toast.LENGTH_LONG).show();
                et_pass.setText("");
                et_passconf.setText("");
                et_pass.requestFocus();
                return;
            }else{
                checkPwd.setText("비밀번호가 일치합니다");
                et_name.requestFocus();
            }

        } else if(v.getId() == R.id.btn_register){
            userEmail= et_email.getText().toString();
            userPwd= et_pass.getText().toString();
            userName= et_name.getText().toString();
            userBirth = txt_birth.getText().toString();


            UserDTO user = new UserDTO(userEmail,userPwd,userName,userBirth);

            userList = new ArrayList<>();
            userList.add(user);
            jObject = new JSONObject();
            try{
                for(int i = 0; i<userList.size(); i++) {

                    jObject.put("email", userList.get(i).getEmail());
                    jObject.put("password", userList.get(i).getPassword());
                    jObject.put("name", userList.get(i).getName());
                    jObject.put("birth", userList.get(i).getBirth());

                }

                Log.d("seul","json Test:" + jObject.toString());
                okHttpConnection.sendToServer_Register(jObject);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}