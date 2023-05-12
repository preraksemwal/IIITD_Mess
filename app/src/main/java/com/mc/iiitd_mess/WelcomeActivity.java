package com.mc.iiitd_mess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.URL;
import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    Button user_button, admin_button;
    SignInButton sign_in_button;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button = (SignInButton) findViewById(R.id.sign_in_button);
        sign_in_button.setSize(SignInButton.SIZE_STANDARD);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account, true);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.d(TAG, String.valueOf(e));
            updateUI(null, true);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updateUI(@Nullable GoogleSignInAccount account, boolean isFirstTime) {
        if (account != null) {
            Log.i(TAG, account.getDisplayName());
            Log.i(TAG, String.valueOf(account.getPhotoUrl()));
            if (Objects.equals(account.getEmail(), "pragyan20226@iiitd.ac.in")) {
                String name = account.getDisplayName();
                DashboardAdminActivity.profileNameString = name;
                Uri url = account.getPhotoUrl();
                DashboardAdminActivity.profilePicUrl = String.valueOf(url);
//                ProfileAdminFragment profileAdminFragment = (ProfileAdminFragment) getSupportFragmentManager().findFragmentById(R.id.frameContainer);
//                profileAdminFragment.updateProfileName(name);
                Intent intent;
                if (isFirstTime) {
                    intent = new Intent(WelcomeActivity.this, TermsAdminActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, DashboardAdminActivity.class);
                }
                startActivity(intent);
            } else {
                String name = account.getDisplayName();
                DashboardUserActivity.profileNameString = name;
                Uri url = account.getPhotoUrl();
                DashboardUserActivity.profilePicUrl = String.valueOf(url);
//                ProfileAdminFragment profileAdminFragment = (ProfileAdminFragment) getSupportFragmentManager().findFragmentById(R.id.frameContainer);
//                profileAdminFragment.updateProfileName(name);
                Intent intent;
                if (isFirstTime) {
                    intent = new Intent(WelcomeActivity.this, TermsUserActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, DashboardUserActivity.class);
                }
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut() {
//        if (mGoogleSignInClient != null) {
            mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.i(TAG, "Log Out");
                    updateUI(null, true);
                    finish();
                }
            });
//        }
    }

    public static void doSignOut(WelcomeActivity activity) {
        activity.signOut();
    }

}