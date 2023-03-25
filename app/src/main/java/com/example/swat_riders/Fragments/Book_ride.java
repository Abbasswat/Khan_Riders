package com.example.swat_riders.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swat_riders.Adapter.Adapter;
import com.example.swat_riders.Model.ModelClass;
import com.example.swat_riders.R;
import com.example.swat_riders.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Book_ride extends Fragment {
    LinearLayoutManager linearLayoutManager;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    List<ModelClass> userList;
    Adapter adapter;

    public Book_ride() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_ride, container, false);
    }
  //  initData();
//        initRecyclerView();
//
//    }
//
}