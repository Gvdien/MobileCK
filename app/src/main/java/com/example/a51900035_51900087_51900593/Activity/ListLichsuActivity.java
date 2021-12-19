package com.example.a51900035_51900087_51900593.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a51900035_51900087_51900593.Fragment.DoxangFragment;
import com.example.a51900035_51900087_51900593.Fragment.SuachuaFragment;
import com.example.a51900035_51900087_51900593.Fragment.ThaynhotFragment;
import com.example.a51900035_51900087_51900593.R;
import com.google.android.material.tabs.TabLayout;

public class ListLichsuActivity extends AppCompatActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lichsu);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Đổ xăng"),true);
        tabLayout.addTab(tabLayout.newTab().setText("Thay nhớt"));
        tabLayout.addTab(tabLayout.newTab().setText("Sửa chữa, thay thế linh kiện"));
        setCurrentTabFragment(0);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurrentTabFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DoxangFragment();
                break;
            case 1:
                fragment = new ThaynhotFragment();
                break;
            case 2:
                fragment = new SuachuaFragment();
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.add_Doxang:
                i = new Intent(this, AddDoxangActivity.class);
                startActivity(i);
                break;
            case R.id.add_Thaynhot:
                i = new Intent(this, AddThaynhotActivity.class);
                startActivity(i);
                break;
            case R.id.add_Suachua:
                i = new Intent(this, AddSuachuaActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}