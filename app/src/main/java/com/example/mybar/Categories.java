package com.example.mybar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Categories extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_xml, container, false);

        //Date to be displayed in list
        String [] drinkCategories = {
                "Cocktail",
                "Coffee / Tea",
                "Milk / Float / Shake",
                "Shot",
                "Smoothie"
        };
        ListView listView= (ListView) view.findViewById(R.id.listviewy);

        //Array Adapter:
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                drinkCategories
        );

        listView.setAdapter(adapter);

        // Handling Click Events in ListView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(view.getContext(), Activity_Cocktail.class);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(view.getContext(), Activity_CoffeeTea.class);
                    startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(view.getContext(), Activity_MilkFloatShake.class);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(view.getContext(), Activity_Shot.class);
                    startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(view.getContext(), Activity_Smoothie.class);
                    startActivity(intent);
                }
            }
        });
        return  view;
    }
}
