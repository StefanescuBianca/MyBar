package com.example.mybar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mybar.ui.main.DrinksAdapter;
import java.util.ArrayList;
import com.example.mybar.Drinksitem;

import static android.app.PendingIntent.getActivity;

 public class Activity_Cocktail extends AppCompatActivity{

    /* private ArrayList<Drinksitem> drinksItems = new ArrayList<>();

     public View onCreateView(@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
         View root = inflater.inflate(R.layout.row_data, container, false);

         RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
         recyclerView.setHasFixedSize(true);
         recyclerView.setAdapter(new DrinksAdapter(drinksItems, getActivity()));
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         drinksItems.add(new Drinksitem(R.drawable.a1, "A1", "0", "0"));
         drinksItems.add(new Drinksitem(R.drawable.army_special, "Army Special", "1", "0"));
         drinksItems.add(new Drinksitem(R.drawable.abbey_martini, "Abbey_martini", "2", "0"));
         drinksItems.add(new Drinksitem(R.drawable.apple_karate, "Apple Karate", "3", "0"));
         drinksItems.add(new Drinksitem(R.drawable.aquamarine, "Aquamarine", "4", "0"));
         drinksItems.add(new Drinksitem(R.drawable.bijou, "Bijou", "5", "0"));
           return  root;
     }*/

     GridView gridView;

    String[] Names = {"A1","Abbey Martini","Apple Karate","Aquamarine", "Army Special", "Bijou"};
    int[] Images = {R.drawable.a1,R.drawable.abbey_martini,R.drawable.apple_karate,R.drawable.aquamarine,R.drawable.army_special, R.drawable.bijou};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cocktail);

        //finding listview
        gridView = findViewById(R.id.gridview4);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),Names[+position], Toast.LENGTH_SHORT).show();
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

