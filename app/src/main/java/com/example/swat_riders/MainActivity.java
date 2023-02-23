package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.swat_riders.Adapter.Adapter;
import com.example.swat_riders.Model.ModelClass;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<ModelClass>userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initRecyclerView();
        
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