package com.example.swat_riders;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swat_riders.Adapter.Adapter;
import com.example.swat_riders.Fragments.history_frag;
import com.example.swat_riders.Fragments.payment_info;
import com.example.swat_riders.Fragments.setting_frag;
import com.example.swat_riders.Model.ModelClass;
import com.example.swat_riders.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.sagarkoli.chetanbottomnavigation.chetanBottomNavigation;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

//import com.example.swat_riders.Adapters.FragmentsAdapter;
import com.example.swat_riders.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    List<ModelClass>userList;
    Adapter adapter;

   TextView textView;
   //navigation drawer
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id==R.id.home){
                Toast.makeText(MainActivity.this, "home fragment", Toast.LENGTH_LONG).show();

            }else if (id==R.id.book_ride){
                Toast.makeText(MainActivity.this, "book_ride fragment", Toast.LENGTH_LONG).show();
            } else if (id==R.id.history) {
              //  Toast.makeText(MainActivity.this, "history fragment", Toast.LENGTH_LONG).show();
                loadFragment(new history_frag());

            } else if (id==R.id.payment_method) {
                Toast.makeText(MainActivity.this, "paymentMethod fragment", Toast.LENGTH_LONG).show();
                loadFragment(new payment_info());
            }else if (id==R.id.setting){
                Toast.makeText(MainActivity.this, "setting fragment", Toast.LENGTH_LONG).show();
                loadFragment(new setting_frag());
            } else if (id==R.id.logout) {

            }else {
                Toast.makeText(MainActivity.this, "Not found any fragment fragment", Toast.LENGTH_LONG).show();

            }
            drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        initData();
        initRecyclerView();
        
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
    }

    private void initData() {

        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));
        userList.add(new ModelClass(R.drawable.mustafa,"Mustafa","10:00 am","how are you","________"));


    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}