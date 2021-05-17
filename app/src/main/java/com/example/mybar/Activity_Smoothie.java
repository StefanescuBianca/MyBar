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

public class Activity_Smoothie extends AppCompatActivity {
    GridView gridView;

    String[] SmoothieNames = {"Kill the cold","Kiwi Papaya","Banana Cantaloupe","Apple Berry", "Strawberry", "Ananas"};
    int[] SmoothieImages = {R.drawable.kill_and_cold,R.drawable.kiwi_papaya,R.drawable.banana_cantaloupe,R.drawable.apple_berry,R.drawable.strawberry, R.drawable.anans};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__smoothie);

        //finding listview
        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),SmoothieNames[+position], Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                //intent.putExtra("name",SmoothieNames[i]);
                //intent.putExtra("image",SmoothieImages[i]);
                //startActivity(intent);

            }
        });


    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return  SmoothieNames.length;
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

            name.setText(SmoothieNames[+position]);
            image.setImageResource(SmoothieImages[+position]);
            return view1;

        }
    }
}