package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseUser;

import id.co.manu.MainActivity;
import id.co.manu.R;
import id.co.manu.viewmodel.AuthViewModel;

public class SplashActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {}, 3000);

        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(SplashActivity.this.getApplication())).get(AuthViewModel.class);

        authViewModel.getUserData().observe(this, firebaseUser -> {
            Intent intent;
            if (firebaseUser == null) {
                // User not authenticated, show sign-in fragment
                intent = new Intent(this, MainActivity.class);
            } else {
                // User authenticated, navigate to NavigationActivity
                intent = new Intent(SplashActivity.this, NavigationActivity.class);
            }
            startActivity(intent);
            finish();
        });

    }
}