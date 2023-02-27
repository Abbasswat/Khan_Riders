package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swat_riders.Adapter.Adapter;
import com.example.swat_riders.Model.ModelClass;
import com.sagarkoli.chetanbottomnavigation.chetanBottomNavigation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<ModelClass>userList;
    Adapter adapter;

    private static final int home =1;
    private static final int like =2;
    private static final int map =3;
    private static final int notificaton =4;
    private static final int payment =5;

    chetanBottomNavigation bottomNavigation;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.navbar);
        textView = findViewById(R.id.btName);

        bottomNavigation.add(new chetanBottomNavigation.Model(home,R.drawable.home));
        bottomNavigation.add(new chetanBottomNavigation.Model(notificaton,R.drawable.bell));
        bottomNavigation.add(new chetanBottomNavigation.Model(map, R.drawable.heart));
        bottomNavigation.add(new chetanBottomNavigation.Model(like,R.drawable.user));
        bottomNavigation.add(new chetanBottomNavigation.Model(payment,R.drawable.heart));

        bottomNavigation.setCount(notificaton,"10");
        bottomNavigation.setOnShowListener(new chetanBottomNavigation.ShowListener(){
            String name;
            @Override
            public void onShowItem(chetanBottomNavigation.Model item) {


                switch (item.getId()){
                    case home:
                        name="home";
                        break;

                    case map:
                        name="map";
                        break;
                    case notificaton:
                        name="bell";
                        break;
                    case payment:
                        name="haert";
                    case like:
                        name="heart";
                        break;
                    default:
                        name="";

                }
                textView.setText(name);

            }
        });
        bottomNavigation.setOnClickMenuListener(new chetanBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(chetanBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "item closed", Toast.LENGTH_SHORT).show();

            }
        });
        bottomNavigation.setOnReselectListener(new chetanBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(chetanBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "item Reselected", Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.show(home,true);


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