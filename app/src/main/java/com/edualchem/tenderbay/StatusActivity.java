package com.edualchem.tenderbay;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baoyachi.stepview.VerticalStepView;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {

    VerticalStepView mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mStep = (VerticalStepView)findViewById(R.id.step_view);
        ArrayList<String> list = new ArrayList<>();
        String[] sr = {"Made a Bid","Bid Submit","Bid Review","Bid Approval"};
        for(int i = 0;i<4;i++){
            list.add(sr[i]);
        }

        mStep.setStepsViewIndicatorComplectingPosition(list.size() - 2)
                .reverseDraw(false)
                .setStepViewTexts(list)
                .setLinePaddingProportion(.85f)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplicationContext(), R.color.grey))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplicationContext(),R.color.grey));


    }
}
