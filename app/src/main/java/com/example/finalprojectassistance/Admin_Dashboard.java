package com.example.finalprojectassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Admin_Dashboard extends AppCompatActivity {

    //initializing
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //Assigning variables
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //adding menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_account_circle_24));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });








    }
}