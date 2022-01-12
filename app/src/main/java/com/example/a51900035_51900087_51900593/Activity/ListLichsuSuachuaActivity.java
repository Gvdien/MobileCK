package com.example.a51900035_51900087_51900593.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900035_51900087_51900593.Adapter.SuachuaAdapter;
import com.example.a51900035_51900087_51900593.Model.Suachua;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListLichsuSuachuaActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView rvSuachua;
    List<Suachua> lstSuachua;
    List<String> mKeys = new ArrayList<>();
    SuachuaAdapter adapter;
    Suachua ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lichsu_suachua);

        rvSuachua = findViewById(R.id.rvSuachua);
        getData();

        rvSuachua.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SuachuaAdapter(this, lstSuachua);
        rvSuachua.setAdapter(adapter);

    }

    public void getData() {
        lstSuachua = new ArrayList<>();
        _myRef = mDatabase.getReference("Suachua");
        _myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Suachua ls = snapshot.getValue(Suachua.class);
                if (ls != null) {
                    lstSuachua.add(ls);
                    String key = snapshot.getKey();
                    mKeys.add(key);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Suachua ls = snapshot.getValue(Suachua.class);
                if (ls == null || lstSuachua == null || lstSuachua.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                lstSuachua.set(index, ls);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Suachua ls = snapshot.getValue(Suachua.class);
                if (ls == null || lstSuachua == null || lstSuachua.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                if (index != -1) {
                    lstSuachua.remove(index);
                    mKeys.remove(index);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent i = new Intent(this, AddSuachuaActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}