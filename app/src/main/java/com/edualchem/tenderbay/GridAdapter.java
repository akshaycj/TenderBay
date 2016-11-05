package com.edualchem.tenderbay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Explore on 8/27/2016.
 */
public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> numb;
    int flag;
    int res[];
    String text[];
    String txt;
    int count;

    public GridAdapter(Context c,int res[],String txt){
        this.mContext = c;
        this.txt = txt;
        this.res = res;
        flag = 0;
    }

    public GridAdapter(Context c,int res[],String text[]){
        this.res = res;
        this.mContext = c;
        this.text = text;
        flag = 1;
    }

    @Override
    public int getCount() {

        return res.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = inflater.inflate(R.layout.grid_item, null);


                ImageView img = (ImageView)grid.findViewById(R.id.grid_img);
                img.setImageResource(res[position]);

                TextView gridText = (TextView)grid.findViewById(R.id.grid_text);
                gridText.setText(text[position]);

        } else {
            grid = (View)convertView;
        }

        return grid;
    }
}
