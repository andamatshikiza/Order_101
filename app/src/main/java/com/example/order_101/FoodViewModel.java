package com.example.order_101;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.order_101.Model.Restaurants;

import java.util.ArrayList;

public class FoodViewModel extends ViewModel {

    MutableLiveData<ArrayList<Restaurants>> res;

    public  void init(Context context){

        if (res!= null){
            return;
        }
        res = Repo.getInstance(context).getScore();
    }

    public LiveData<ArrayList<Restaurants>> getRestaurent(){

        return res;
    }
}
