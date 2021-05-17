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

public class Activity_CoffeeTea extends AppCompatActivity {
    GridView gridView;

    String[] Names = {"Frappe","Iced Coffee","Irish Coffee","Thai Iced Tea", "Masala Chai", "Kurant Tea"};
    int[] Images = {R.drawable.frappee,R.drawable.iced_coffee,R.drawable.irish_coffee,R.drawable.thai_ice_tea,R.drawable.masala_chai, R.drawable.kurant_tea};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__coffee_tea);

        gridView = findViewById(R.id.gridview3);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(),Names[+position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Activity_Item.class);
                intent.putExtra("name",Names[+position]);
                intent.putExtra("image",Images[+position]);
                startActivity(intent);

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

            TextView name = view1.findViewById(R.id.name);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(Names[+position]);
            image.setImageResource(Images[+position]);
            return view1;

        }
    }
}