package id.co.manu.views;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import id.co.manu.R;

public class NavigationActivity extends AppCompatActivity implements HomeFragment.OnSearchButtonClickListener {

    private NavigationBarView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Manu);
        setContentView(R.layout.activity_navigation);

        bottomNavBar = findViewById(R.id.bottomNavigationView);

        bottomNavBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if(itemId == R.id.mnHome){
                loadPage(new HomeFragment(), false);
            }else if(itemId == R.id.mnExplore){
                loadPage(new ExploreFragment(), false);
            }else if(itemId == R.id.mnReport){
                loadPage(new ReportFragment(), false);
            }else if(itemId == R.id.mnProfile){
                loadPage(new ProfileFragment(), false);
            }
            return true;
        });
        loadPage(new HomeFragment(), true);
    }

    private void loadPage(Fragment fragment, boolean isInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isInitialized){
            fragmentTransaction.add(R.id.frameLayout, fragment);
        }else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onSearchButtonClick() {
        bottomNavBar.setSelectedItemId(R.id.mnExplore);
    }
}