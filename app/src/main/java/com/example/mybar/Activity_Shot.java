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

public class Activity_Shot extends AppCompatActivity {
    GridView gridView;

    String[] ShotNames = {"3 Wise Men","747","Big Red","Bob Marley", "Bubble Gum", "Apple Slammer"};
    int[] ShotImages = {R.drawable.wise_men,R.drawable.s747,R.drawable.big_red,R.drawable.bob_marley,R.drawable.bubble_gum, R.drawable.apple_slamer};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__shot);

        //finding listview
        gridView = findViewById(R.id.gridview1);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),ShotNames[+position], Toast.LENGTH_SHORT).show();
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
            return  ShotNames.length;
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

            name.setText(ShotNames[+position]);
            image.setImageResource(ShotImages[+position]);
            return view1;

        }
    }
}