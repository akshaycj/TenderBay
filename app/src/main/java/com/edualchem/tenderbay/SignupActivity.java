package com.edualchem.tenderbay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    FirebaseAuth auth;
    String email,pass;
    EditText emailt,passt;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {

            actionBar.hide();

        }


        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");

        auth = FirebaseAuth.getInstance();
       // MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        Button signup = (Button)findViewById(R.id.signup_submit);
        emailt = (EditText)findViewById(R.id.signup_email);
        passt = (EditText)findViewById(R.id.signup_pass);

        pref = getSharedPreferences("email", Context.MODE_PRIVATE);
        editor = pref.edit();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailt.getText().toString();
                pass = passt.getText().toString();
                progressDialog.show();
                createAccount();

            }
        });

        Button reg = (Button)findViewById(R.id.login_from_reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
/*
        spinner.setItems("","Boy", "Girl");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

            }
        });
*/

    }

    private void createAccount(){


        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                Log.e("complete","enetered");
                if(task.isSuccessful())
                {
                    Log.e("complete sucess","sucess");

                    Toast.makeText(SignupActivity.this,"Account Created!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                }
                else if(!task.isSuccessful())
                { Toast.makeText(SignupActivity.this,"Sign Up failed!", Toast.LENGTH_SHORT).show();}

            }
        });
    }

}
