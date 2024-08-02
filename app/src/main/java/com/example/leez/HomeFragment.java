package com.example.leez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Shop> shopList;
    private SearchView searchView;


    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeShops();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        searchView = view.findViewById(R.id.SearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ShopAdapter shopAdapter = new ShopAdapter(shopList);
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        // Set item click listener for the ShopAdapter
        shopAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Shop shop) {
                // Handle the click event, open ShopActivity with shop details
                Intent intent = new Intent(requireContext(), ShopActivity.class);
                intent.putExtra("shopName", shop.getName());
                intent.putExtra("shopAddress", shop.getAddress());
                startActivity(intent);
            }
        });

        return view;
    }

    private void filterList(String text) {
        List<Shop> filteredList=new ArrayList<>();
        for (Shop shop: shopList){
            if(shop.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(shop);

            }
        }
        if(filteredList.isEmpty()) {
            Toast.makeText(getContext(), "Shop not found", Toast.LENGTH_SHORT).show();
        }else{
            ShopAdapter shopAdapter = new ShopAdapter(filteredList);
            shopAdapter.setFilteredList(filteredList);

        }

    }

    private void initializeShops() {
        // Initialize your list of shops. Replace this with your actual shop data.
        shopList = new ArrayList<>();
        shopList.add(new Shop("LeeMart", "123 Main St"));
        // Add more shops as needed.
    }
}
