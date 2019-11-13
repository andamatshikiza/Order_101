package com.example.order_101;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.order_101.Model.Restaurants;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class Repo {
    static Repo instances;
    private ArrayList<Restaurants> restaurants = new ArrayList<>();
    static Context mContext;
    static IDataListiner dataListener;

    public static Repo getInstance(Context context) {

        mContext = context;
        if (instances == null) {
            instances = new Repo();

        }
        dataListener = (IDataListiner) mContext;
        return instances;

    }

    public MutableLiveData<ArrayList<Restaurants>> getScore() {

        loadScore();
        MutableLiveData<ArrayList<Restaurants>> score = new MutableLiveData<>();
        score.setValue(restaurants);

        return score;
    }

    private void loadScore() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseInstanceId.getInstance().getToken();
        db.collection("Category")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {


                            restaurants.add(documentSnapshot.toObject(Restaurants.class));
                        }
                        dataListener.loadData();
                    }
                });
    }
}