package com.example.mybar;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_MilkFloatShake extends AppCompatActivity {
    GridView gridView;

    String[] Names = {"Sweet Bananas","Banana Strawberry Shake","Avalanche","Chocolate Monkey", "Banana MilkShake", "Butter Baby"};
    int[] Images = {R.drawable.sweet_banana,R.drawable.banana_strawberry,R.drawable.avalanche,R.drawable.chocolate_monkey,R.drawable.banana_milkshake, R.drawable.butter_baby};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__milk_float_shake);

        //finding listview
        gridView = findViewById(R.id.gridview2);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),Names[+position], Toast.LENGTH_SHORT).show();
                // Intent intent = new  /K Intent(getApplicationContext(),GridItemActivity.class);
                //intent.putExtra("name",SmoothieNames[i]);
                //intent.putExtra("image",SmoothieImages[i]);
                //startActivity(intent);

            }
        });


    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return  Names.length;
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
        public View getView(int position, View view, ViewGroup viewGroup) {

            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_smoothie
            TextView name = view1.findViewById(R.id.name);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(Names[+position]);
            image.setImageResource(Images[+position]);
            return view1;

        }
    }
}