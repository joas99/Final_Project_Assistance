package com.example.finalprojectassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
                //Initializing fragment
                Fragment fragment = null;
                //checking condition
                switch (item.getId()){
                    case 1:
                        //when id is 1
                        //initialize fragment 1
                        fragment = new AdminNotificationFragment();
                        break;
                    case 2:
                        //when id is 1
                        //initialize fragment 2
                        fragment = new AdminHomeFragment();
                        break;

                    case 3:
                        //when id is 1
                        //initialize fragment 3
                        fragment = new AdminAccountFragment();
                        break;
                }//end switch

                //Loading the fragment
                loadFragment(fragment);

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


        //SETTING THE NOTIFICATIONS COUNT NUMBER
        bottomNavigation.setCount(1,"3");

        //setting home fragment initially selected
        bottomNavigation.show(2,true);










    }
//Method loading fragment
    private void loadFragment(Fragment fragment) {

        //Replacing fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();

    }
}