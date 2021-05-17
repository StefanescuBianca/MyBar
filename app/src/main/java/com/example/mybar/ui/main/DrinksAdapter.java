package com.example.mybar.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybar.Drinksitem;
import com.example.mybar.FavDB;
import com.example.mybar.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;


import java.util.ArrayList;
import java.util.logging.Handler;

import static android.os.Looper.getMainLooper;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    private ArrayList<Drinksitem> drinksItems;
    private Context context;
    private FavDB favDB;

    public DrinksAdapter(ArrayList<Drinksitem> drinksItems,Context context){
        this.drinksItems = drinksItems;
        this.context=context;
    }

    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if(firstStart){
            createTableOnFirstStart();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_data,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder viewHolder, int position) {
        final Drinksitem drinksItem = drinksItems.get(position);

        readCursorData(drinksItem,viewHolder);
        viewHolder.images.setImageResource(drinksItem.getImageResourse());
        viewHolder.name.setText(drinksItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return drinksItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView images;
        TextView name;
        Button favBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            images = itemView.findViewById(R.id.images);
            name = itemView.findViewById(R.id.name);
            favBtn = itemView.findViewById(R.id.favBtn);



            //add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Drinksitem drinksItem = drinksItems.get(position);

                    if(drinksItem.getFavStatus().equals("0")){
                        drinksItem.setFavStatus("1");
                        favDB.insertIntoDatabase(drinksItem.getTitle(),drinksItem.getImageResourse(),
                                drinksItem.getKey_id(),drinksItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);
                    }else{
                        drinksItem.setFavStatus("0");
                        favDB.remove_fav(drinksItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic__favorite_shadow_24);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    private void readCursorData(Drinksitem drinksItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(drinksItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                drinksItem.setFavStatus(item_fav_status);

                //ckeck fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic__favorite_shadow_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }
       /* // like click
        private void likeClick (Drinksitem drinksItem, Button favBtn, final TextView textLike) {
            DatabaseReference refLike = FirebaseDatabase.getInstance().getReference().child("likes");
            final DatabaseReference upvotesRefLike = refLike.child(drinksItem.getKey_id());

            if (drinksItem.getFavStatus().equals("0")) {

                drinksItem.setFavStatus("1");
                favDB.insertIntoDatabase(drinksItem.getTitle(), drinksItem.getImageResourse(),
                        drinksItem.getKey_id(), drinksItem.getFavStatus());
                favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);
                favBtn.setSelected(true);

                upvotesRefLike.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
                        try {
                            Integer currentValue = mutableData.getValue(Integer.class);
                            if (currentValue == null) {
                                mutableData.setValue(1);
                            } else {
                                mutableData.setValue(currentValue + 1);
                                new Handler(getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        textLike.setText(mutableData.getValue().toString());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        System.out.println("Transaction completed");
                    }
                });



            } else if (drinksItem.getFavStatus().equals("1")) {
                drinksItem.setFavStatus("0");
                favDB.remove_fav(drinksItem.getKey_id());
                favBtn.setBackgroundResource(R.drawable.ic__favorite_shadow_24);
                favBtn.setSelected(false);

                upvotesRefLike.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
                        try {
                            Integer currentValue = mutableData.getValue(Integer.class);
                            if (currentValue == null) {
                                mutableData.setValue(1);
                            } else {
                                mutableData.setValue(currentValue - 1);
                                new Handler(getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        textLike.setText(mutableData.getValue().toString());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        System.out.println("Transaction completed");
                    }
                });
            }*/
}

