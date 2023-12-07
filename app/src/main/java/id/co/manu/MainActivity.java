package id.co.manu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseUser;

import id.co.manu.repository.AuthenticationRepo;
import id.co.manu.viewmodel.AuthViewModel;
import id.co.manu.views.ExploreFragment;
import id.co.manu.views.HomeFragment;
import id.co.manu.views.NavigationActivity;
import id.co.manu.views.ProfileFragment;
import id.co.manu.views.ReportFragment;
import id.co.manu.views.SignInFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SignInFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment);
        fragmentTransaction.commit();

    }
}