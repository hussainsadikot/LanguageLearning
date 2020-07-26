package com.example.android.languagelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.languagelearning.Model.Users;
import com.example.android.languagelearning.Prevalent.Prevalent;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText InputNumber, Inputpassword;
    private Button LoginButton;
    private ProgressDialog loadingbar;
    private String parentDBname = "User";
    private CheckBox checkBoxRememberMe;
    private TextView AdminLink, NotAdminLink;
    public static final String BASE_URL = "";
    public static Retrofit retrofit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InputNumber = findViewById(R.id.et_phone_number_login);
        Inputpassword = findViewById(R.id.et_pwd_login);
        LoginButton = findViewById(R.id.login_btn);
        AdminLink = findViewById(R.id.admin_link_tv);
        NotAdminLink = findViewById(R.id.not_admin_link_tv);
        loadingbar = new ProgressDialog(this);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.remeber_me_chkbox);
        Paper.init(this);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDBname = "Admin";
            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDBname = "User";
            }
        });
    }
    private void login() {

        String phone = InputNumber.getText().toString().trim();
        String password = Inputpassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please Write Your Number", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Write Password ", Toast.LENGTH_SHORT).show();
        } else {
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("Please Wait, While we are checking credential");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            AllowAccess(phone, password);

            CheckingCredential(phone, password);

        }


    }



    private void CheckingCredential(final String phone, final String password) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Users>> call = jsonPlaceHolderAPI.getUser();
        //We can Use Direct Url if we have URL for user email and pass word
//        Call<List<Users>> call = jsonPlaceHolderAPI.getUser("Paste Url Here");

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Users> users = response.body();
                for (Users users1 : users) {
                    String phonenumberfromdb = users1.getEmail();
                    String passwordfromdb = users1.getPassword();
                    if (phone.equals(phonenumberfromdb) && password.equals(passwordfromdb)) {
                        Toast.makeText(LoginActivity.this, "Credential Match, Redirect to App Home", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error Message:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AllowAccess(final String phone, final String password) {
        if (checkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
    }
    // check here credential with database



}