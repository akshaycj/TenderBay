package com.edualchem.tenderbay;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidadvance.androidsurvey.SurveyActivity;
import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar!= null){

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);



        }

        ListView theListView = (ListView) findViewById(R.id.mainListView);

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("Hell");
        list.add("Mel");
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(getApplicationContext(),list);

        theListView.setAdapter(adapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ((FoldingCell) view).toggle(false);

                adapter.registerToggle(pos);
            }

        });



    }

    public class FoldingCellListAdapter extends ArrayAdapter<String> {
        private static final int SURVEY_REQUEST = 1337;

        private HashSet<Integer> unfoldedIndexes = new HashSet<>();

        Context c;
        public FoldingCellListAdapter(Context context, List<String> objects) {
            super(context, 0, objects);
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {



            FoldingCell cell = (FoldingCell) convertView;
            final ViewHolder viewHolder;


            if(cell == null){

                viewHolder = new ViewHolder();



                LayoutInflater li = LayoutInflater.from(getContext());

                cell = (FoldingCell)li.inflate(R.layout.cell,parent,false);
                viewHolder.btn = (Button)cell.findViewById(R.id.btn_make_a_bid);
                viewHolder.bt = (Button)cell.findViewById(R.id.close);
                cell.setTag(viewHolder);

            }
            else {
                viewHolder = (ViewHolder)cell.getTag();
                if (unfoldedIndexes.contains(position)) {
                    cell.unfold(true);
                } else {
                    cell.fold(true);
                }

            }
            final FoldingCell fc = cell;



            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                Intent i  =new Intent(getApplicationContext(),SurveyActivity.class);
                    i.putExtra("json_survey",loadSurveyJson("question.json"));
                startActivityForResult(i,SURVEY_REQUEST);
                    fc.toggle(false);

                    registerToggle(position);


                }
            });

            viewHolder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);

                    registerToggle(position);
                }
            });





            return cell;

        }



        private String loadSurveyJson(String filename) {
            try {
                InputStream is = getAssets().open(filename);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                return new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }


        public void registerToggle(int position) {
            if (unfoldedIndexes.contains(position))
                registerFold(position);
            else
                registerUnfold(position);
        }

        public void registerFold(int position) {
            unfoldedIndexes.remove(position);
        }

        public void registerUnfold(int position) {
            unfoldedIndexes.add(position);
        }




    }

    public class ViewHolder{
       public Button btn;

        Button bt;
        public ViewHolder(){

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
