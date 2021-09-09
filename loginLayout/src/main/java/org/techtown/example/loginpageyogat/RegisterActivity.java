package org.techtown.example.loginpageyogat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_pass, et_name, et_email, et_passconf, et_birth;
    private Button btn_register, btn_pwdCheck;

    private String responseData;
    private String pwd, pwdConf;

    private String userEmail, userPwd, userName, userBirth;

    private List<UserDTO> userList;

    private OKHttpConnection okHttpConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);

        okHttpConnection = new OKHttpConnection();

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById( R.id.et_pass );
        et_passconf = findViewById(R.id.et_passconf);
        et_name = findViewById( R.id.et_name );
        et_birth = findViewById(R.id.et_birth);
        btn_pwdCheck = findViewById(R.id.btn_pwdCheck);
        btn_register = findViewById( R.id.btn_register );



        //회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(this);
        btn_pwdCheck.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_pwdCheck){
            pwd = et_pass.getText().toString();
            pwdConf = et_passconf.getText().toString();

            if(pwd != pwdConf) {
                Toast.makeText(RegisterActivity.this,"비밀번호가 다릅니다. 다시 입력해주세요 ",Toast.LENGTH_LONG).show();
                et_pass.setText("");
                et_passconf.setText("");
                et_pass.requestFocus();
                return;
            }
        } else if(v.getId() == R.id.btn_register){
            userEmail= et_email.getText().toString();
            userPwd= et_pass.getText().toString();
            userName= et_name.getText().toString();
            userBirth = et_birth.getText().toString();
            okHttpConnection.sendToServer_Register();

            UserDTO user = new UserDTO(userEmail,userPwd,userName,userBirth);

            userList = new ArrayList<>();
            userList.add(user);

            try{
                JSONArray jArray = new JSONArray();
                for(int i = 0; i<userList.size(); i++){

                    JSONObject jObject = new JSONObject();
                    jObject.put("email",userList.get(i).getEmail());
                    jObject.put("password",userList.get(i).getPassword());
                    jObject.put("name",userList.get(i).getName());
                    jObject.put("birth",userList.get(i).getBirth());
                    jArray.put(jObject);
                }

                Log.d("seul","json Test:" + jArray.toString());
            }catch (JSONException e){
                e.printStackTrace();
            }



        }

    }


}