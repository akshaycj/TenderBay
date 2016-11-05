package com.edualchem.tenderbay;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference dbref;
    ProgressDialog progressDialog;
    EditText name,email,mobile,org,pos;
    Button btn;
    FirebaseUser fbUser;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        auth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance();

        fbUser = auth.getCurrentUser();



        final Person person = new Person();

        name = (EditText)findViewById(R.id.c_name);
        email  = (EditText)findViewById(R.id.c_email);
        mobile = (EditText)findViewById(R.id.c_mob);
        org = (EditText)findViewById(R.id.c_org);
        pos = (EditText)findViewById(R.id.c_pos);

        btn = (Button)findViewById(R.id.btn_create);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = fbUser.getUid();
                person.setName(name.getText().toString());
                person.setEmail(email.getText().toString());
                person.setMobile(mobile.getText().toString());
                person.setOrg(org.getText().toString());
                person.setPosition(pos.getText().toString());
                person.setUid(uid);
                dbref = db.getReference("user_details");
                dbref.child(uid).setValue(person);
                Toast.makeText(getApplicationContext(),"Profile Created!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });



    }
}
