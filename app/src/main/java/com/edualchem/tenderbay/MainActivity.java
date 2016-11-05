package com.edualchem.tenderbay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] cat = {"Projects","Status","Profile","Alerts"};

    int[] id = {R.drawable.ic_projects,R.drawable.ic_status,R.drawable.ic_profile,R.drawable.ic_notification};

    String[] profile = {"Create","View","Update"};

    int[] pro_id={R.drawable.ic_create,R.drawable.ic_view,R.drawable.ic_update};

int level = 0;

    GridAdapter adapter;
    GridView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (GridView)findViewById(R.id.grid);

        setGrid(id,cat);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(level == 0){

                    switch (position){
                        case 0:
                            startActivity(new Intent(MainActivity.this,ProjectsActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this,StatusActivity.class));
                            break;
                        case 2:
                            setGrid(pro_id,profile);
                            ++level;
                            break;
                    }
                }

                if (level ==1){
                    switch (position){
                        case 0:
                            startActivity(new Intent(MainActivity.this,CreateProfileActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this,ProfileViewActivity.class));
                            break;
                    }
                }
            }
        });




    }

    public void setGrid(int arr[],String[] text){

        GridAdapter adapter = new GridAdapter(MainActivity.this,arr,text);

        view.setAdapter(adapter);

    }

    @Override
    public void onBackPressed(){
        if(level==0){

            finish();
        }
        else {


            GridAdapter adapter = new GridAdapter(MainActivity.this,id,cat);

            view.setAdapter(adapter);

            level=0;
        }
    }
}
