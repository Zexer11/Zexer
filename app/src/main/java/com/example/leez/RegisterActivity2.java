package com.example.leez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {

    EditText edOrganisationName,edOrganisationEmail,edOrganisationPassword,edOrganisationConfirmPassword;
    Button btnOrgRegister;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        edOrganisationName=findViewById(R.id.editTextOrgName);
        edOrganisationPassword=findViewById(R.id.editTextOrgPassword);
        edOrganisationEmail=findViewById(R.id.editTextOrgEmail);
        edOrganisationConfirmPassword=findViewById(R.id.editTextOrgConfirmPassword);
        btnOrgRegister=findViewById(R.id.buttonOrgRegister);



        btnOrgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edOrganisationName.getText().toString();
                String email=edOrganisationEmail.getText().toString();
                String password=edOrganisationPassword.getText().toString();
                String confirm=edOrganisationConfirmPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"leez",null,1);
                if(username.length()==0||email.length()==0||password.length()==0||confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0) {
                        if (isValid(password)) {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Account saved",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity2.this, com.example.leez.LoginActivity2.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters,having letter,digit and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8) {
            return false;
        }else{
            for(int p=0;p<passwordhere.length();p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for(int r=0;r<passwordhere.length();r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for(int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;

        }
    }
}
