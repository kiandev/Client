package com.kiandev.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private int currentId;
    private Boolean exit = false;
    public static final String TAG = "kiandev_client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_home);

        fragmentManager = getSupportFragmentManager();
        fragment = new Home_Fragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, fragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (currentId == item.getItemId()) {
                    return false;
                }
                currentId = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.item_home:
                        fragment = new Home_Fragment();
                        break;
//                    case R.id.admin_item_customer:
//                        fragment = new Admin_Fragment_Customer();
//                        break;
//                    case R.id.admin_item_advisor:
//                        fragment = new Admin_Fragment_Advisor();
//                        break;
//                    case R.id.admin_item_project:
//                        fragment = new Selling_Fragment_Project();
//                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment).commit();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "برای خروج کلید بازگشت را مجدد فشار دهید !",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}
