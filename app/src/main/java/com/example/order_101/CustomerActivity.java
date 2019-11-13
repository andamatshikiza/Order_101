package com.example.order_101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.order_101.Model.Restaurants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.order_101.R.*;
import static com.example.order_101.R.layout.list_item_res;

public class CustomerActivity extends AppCompatActivity implements IDataListiner{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    String categoryId = "";
    FirebaseDatabase database;
    DatabaseReference category;
    RestaurantAp restaurantAp;
    RecyclerView recyclerView ;
    FoodViewModel foodViewModel;
    private LinearLayoutManager layoutManager;
     private  AppBarConfiguration appBarConfiguration;
    FirebaseRecyclerAdapter<Restaurants,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        setContentView(layout.customer_layout);
        drawerLayout = findViewById(id.drawer_layout);
        toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
          ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
@Override
public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        drawerLayout.closeDrawers();

        int id = menuItem.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        recyclerView = (RecyclerView)findViewById(R.id.restList);
        recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);



        if (id== R.id.nav_restu){
            transaction.replace(R.id.framev,new RestaurantFragment()).commit();
        }else {
            if (id== R.id.nav_tray){
                transaction.replace(R.id.framev,new TrayFragment()).commit();
            }else{
                if (id== R.id.nav_order){

                }else{
                    if (id==R.id.nav_logout){

                    }
                }
            }
        }

        return true;
        }
        });
        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.replace(id.framev,new RestaurantFragment()).commit();
        }





    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:

                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
        }

    @Override
    public void loadData() {

        foodViewModel.getRestaurent().observe(this, new Observer<ArrayList<Restaurants>>() {
            @Override
            public void onChanged(ArrayList<Restaurants> scoreboards) {
                restaurantAp.notifyDataSetChanged();
            }
        });
    }
}
