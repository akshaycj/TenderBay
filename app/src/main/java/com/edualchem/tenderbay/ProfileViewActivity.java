package com.edualchem.tenderbay;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileViewActivity extends AppCompatActivity {


    DatabaseReference dbref;
    FirebaseUser user;
    FirebaseAuth auth;
    String uid;
    Person person;

    ProgressDialog progressDialog;

    TextView name,email,mob,org,pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        if (user!=null){
            uid = user.getUid();
        }

        progressDialog = new ProgressDialog(ProfileViewActivity.this);

        progressDialog.setMessage("Fetching your data..");
        progressDialog.show();
        person = new Person();

        name = (TextView)findViewById(R.id.v_name);
        email = (TextView)findViewById(R.id.v_email);
        mob = (TextView)findViewById(R.id.v_mob);
        org = (TextView)findViewById(R.id.v_org);
        pos = (TextView)findViewById(R.id.v_pos);

        dbref = FirebaseDatabase.getInstance().getReference("user_details");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Person person = child.getValue(Person.class);

                    name.setText("Name:    "+person.getName());
                    email.setText("E-mail:    "+person.getEmail());
                    mob.setText("Mobile:    "+person.getMobile());
                    org.setText("Org:    "+person.getOrg());
                    pos.setText("Pos:    "+person.getPosition());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbref.addListenerForSingleValueEvent(listener);








    }
}
