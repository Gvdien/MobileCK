package com.example.a51900035_51900087_51900593.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a51900035_51900087_51900593.Model.Lichsu;
import com.example.a51900035_51900087_51900593.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class ViewLichsuSuachuaActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    TextView tvNoithuchien, tvThoigian, tvChiphi;
    Lichsu ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lichsu_suachua);

        inItUI();
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){return;}
        ls = (Lichsu)bundle.get("object_lichsu");
        tvNoithuchien.setText(ls.getNoithuchien().toUpperCase());
        tvThoigian.setText(ls.getThoigian());
        tvChiphi.setText(Integer.toString(ls.getChiphi()));
    }

    private void inItUI() {
        tvNoithuchien = findViewById(R.id.tvNoithuchien);
        tvThoigian = findViewById(R.id.tvThoigian);
        tvChiphi = findViewById(R.id.tvChiphi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_bottom_edit:
                finish();
                onClickUpdate(ls);
                break;
            case R.id.menu_bottom_delete:
                onClickDelete(ls);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickDelete(Lichsu ls) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa lịch sử")
                .setMessage("Bạn có chắc chắn muốn xóa lịch sử này không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _myRef = mDatabase.getReference("Suachua");
                        _myRef.child(ls.getId()).removeValue();
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void onClickUpdate(Lichsu ls) {
        Intent i = new Intent(this, AddSuachuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ob_suachua", (Serializable) ls);
        i.putExtras(bundle);
        startActivity(i);
    }


}