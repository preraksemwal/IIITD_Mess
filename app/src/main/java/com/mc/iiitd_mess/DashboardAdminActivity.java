package com.mc.iiitd_mess;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class DashboardAdminActivity extends AppCompatActivity {
    ImageButton menuButton, homeButton, profileButton;
    public static String profileNameString, profilePicUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard_admin);

        HomeAdminFragment homeAdminFragment = new HomeAdminFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, homeAdminFragment);
        fragmentTransaction.commit();

        menuButton = (ImageButton) findViewById(R.id.menuButton);
        homeButton = (ImageButton) findViewById(R.id.homeButton);
        profileButton = (ImageButton) findViewById(R.id.profileButton);

        homeButton.setBackgroundResource(R.color.black);
        homeButton.setImageResource(R.drawable.home_white);
        menuButton.setBackgroundResource(R.color.white);
        menuButton.setImageResource(R.drawable.menu_black);
        profileButton.setBackgroundResource(R.color.white);
        profileButton.setImageResource(R.drawable.user_black);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuAdminFragment menuAdminFragment = new MenuAdminFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, menuAdminFragment);
                fragmentTransaction.commit();
                menuButton.setBackgroundResource(R.color.black);
                menuButton.setImageResource(R.drawable.menu_white);

                homeButton.setBackgroundResource(R.color.white);
                homeButton.setImageResource(R.drawable.home_black);
                profileButton.setBackgroundResource(R.color.white);
                profileButton.setImageResource(R.drawable.user_black);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAdminFragment homeAdminFragment = new HomeAdminFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeAdminFragment);
                fragmentTransaction.commit();
                homeButton.setBackgroundResource(R.color.black);
                homeButton.setImageResource(R.drawable.home_white);

                menuButton.setBackgroundResource(R.color.white);
                menuButton.setImageResource(R.drawable.menu_black);
                profileButton.setBackgroundResource(R.color.white);
                profileButton.setImageResource(R.drawable.user_black);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileAdminFragment profileAdminFragment = new ProfileAdminFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, profileAdminFragment);
                fragmentTransaction.commit();
                profileButton.setBackgroundResource(R.color.black);
                profileButton.setImageResource(R.drawable.user_white);

                homeButton.setBackgroundResource(R.color.white);
                homeButton.setImageResource(R.drawable.home_black);
                menuButton.setBackgroundResource(R.color.white);
                menuButton.setImageResource(R.drawable.menu_black);
                profileAdminFragment.updateProfileName(profileNameString);
                profileAdminFragment.updateProfilePic(profilePicUrl);
            }
        });

    }
}
