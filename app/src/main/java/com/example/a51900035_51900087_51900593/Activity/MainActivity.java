package com.example.a51900035_51900087_51900593.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51900035_51900087_51900593.Fragment.EmergencyFragment;
import com.example.a51900035_51900087_51900593.Fragment.HistoryFragment;
import com.example.a51900035_51900087_51900593.Fragment.MyProfileFragment;
import com.example.a51900035_51900087_51900593.Fragment.NoficationsFragment;
import com.example.a51900035_51900087_51900593.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener  {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_NOFICATIONS = 1;
    private static final int FRAGMENT_HISTORY = 2;
    private static final int FRAGMENT_MY_PROFILE = 3;
    private static final int FRAGMENT_EMERGENCY = 4;
    private static final int FRAGMENT_SIGNOUT = 5;
    public static  final  int MY_REQUEST_CODE = 10;
    private  int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private TextView tv_name,tv_email;
    private ImageView imgAvatar;
    String personName;
    String personGivenName;
    String personFamilyName;
    String personEmail;
    String personId;
    Uri personPhoto;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                ,mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HistoryFragment());
        navigationView.getMenu().findItem(R.id.nav_history).setChecked(true);
        showUserInformation();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
//        if(id == R.id.nav_home){
//            if(mCurrentFragment != FRAGMENT_HOME){
//                replaceFragment(new HistoryFragment());
//                mCurrentFragment = FRAGMENT_HOME;
//            }
//        }
         if (id ==R.id.nav_sign_out){
            if(mCurrentFragment != FRAGMENT_SIGNOUT){
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        });
            }
        } else if(id == R.id.nav_my_profile){
            if(mCurrentFragment != FRAGMENT_MY_PROFILE){
                replaceFragment(new MyProfileFragment());
                mCurrentFragment = FRAGMENT_MY_PROFILE;
//                navigationView.getMenu().findItem(R.id.nav_my_profile).setChecked(true);
            }
        }

        else if(id == R.id.nav_nofications){
            if(mCurrentFragment != FRAGMENT_NOFICATIONS){
                replaceFragment(new NoficationsFragment());
                mCurrentFragment = FRAGMENT_NOFICATIONS;
            }
        }

        else if(id == R.id.nav_history){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }

        else if(id == R.id.nav_emergency){
            if(mCurrentFragment != FRAGMENT_EMERGENCY){
                replaceFragment(new EmergencyFragment());
                mCurrentFragment = FRAGMENT_EMERGENCY;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init(){
        navigationView = findViewById(R.id.navigation_view);
        imgAvatar = navigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tv_name = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tv_email = navigationView.getHeaderView(0).findViewById(R.id.tv_email);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showUserInformation(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
             personName = acct.getDisplayName();
             personGivenName = acct.getGivenName();
             personFamilyName = acct.getFamilyName();
             personEmail = acct.getEmail();
             personId = acct.getId();
             personPhoto = acct.getPhotoUrl();
        }

            tv_name.setVisibility(View.VISIBLE);
            tv_name.setText(personName);
            tv_email.setText(personEmail);
            Glide.with(this).load(personPhoto).error(R.drawable.ic_avatart_default).into(imgAvatar);

    }

    private  void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == MY_REQUEST_CODE){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                openGallery();
//            } else {
//                Toast.makeText(this, "Please give permission for this action", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    public void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select img"));
//    }
}