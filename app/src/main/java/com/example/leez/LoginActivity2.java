package com.example.leez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {

    EditText edOrganisationName, edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edOrganisationName = findViewById(R.id.editTextOrgEmail);
        edPassword = findViewById(R.id.editTextOrgPassword);
        btn = findViewById(R.id.buttonOrgLogin);
        tv = findViewById(R.id.textViewNewUser2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edOrganisationName.getText().toString();
                String Password = edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"leez",null,1);
                if (Username.length() == 0 || Password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                } else {
                    if(db.login(Username,Password)==1){
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("Organisation Email",Username);
                        //to save our data with key and value.
                        editor.apply();
                        startActivity(new Intent(LoginActivity2.this, HomeActivity2.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Name or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity2.this,RegisterActivity2.class));
            }
        });


    }
}