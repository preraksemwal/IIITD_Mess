package com.mc.iiitd_mess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ProfileAdminFragment extends Fragment {
    private final String TAG = "ProfileAdminFragment";
    private static String profileNameString = "John Doe";
    private static String profilePicUrl = "";
    TextView profileName;
    ImageView profPic;

    Button logout_button;
    public void updateProfileName(String name) {
        profileNameString = name;
    }

    public void updateProfilePic(String url) {
        profilePicUrl = url;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile_admin, container, false);
        Toast.makeText(view.getContext(), "Admin Profile", Toast.LENGTH_SHORT).show();

        profileName = (TextView) view.findViewById(R.id.profileName);
//        Log.i(TAG, profileNameString);
        profileName.setText(profileNameString);

        profPic = (ImageView) view.findViewById(R.id.profpic);
        String url = profilePicUrl;
        Picasso.get().load(url).into(profPic);
        logout_button = view.findViewById(R.id.logout_button);
        // Set an OnClickListener for the Button
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new Activity
                WelcomeActivity welcomeActivity = new WelcomeActivity();
                welcomeActivity.doSignOut(welcomeActivity);
//                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
//                startActivity(intent);
            }
        });
        return view;
    }
}