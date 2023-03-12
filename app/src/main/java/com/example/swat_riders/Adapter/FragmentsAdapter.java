package com.example.swat_riders.Adapter;

import android.provider.ContactsContract;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.swat_riders.Fragments.Book_ride;
import com.example.swat_riders.Fragments.Local_calls;
import com.example.swat_riders.Fragments.Map;
import com.example.swat_riders.Fragments.Payment_methods;
import com.example.swat_riders.Fragments.payment_info;

public class FragmentsAdapter extends FragmentStateAdapter {

    private String[] titles= new String[]{"Pay","Book_ride","Map","localCalls","Profile"};
    public FragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Payment_methods();
            case 1:
                return new Book_ride();
            case 2:
                return new Map();
            case 3:
                return new Local_calls();
            case 4:
                return new payment_info();

        }
        return new Book_ride();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
