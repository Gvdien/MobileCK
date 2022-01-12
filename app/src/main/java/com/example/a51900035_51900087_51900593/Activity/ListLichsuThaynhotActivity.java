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

import com.example.a51900035_51900087_51900593.Adapter.ThaynhotAdapter;
import com.example.a51900035_51900087_51900593.Model.Thaynhot;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListLichsuThaynhotActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    RecyclerView rvThaynhot;
    List<Thaynhot> lstThaynhot;
    List<String> mKeys = new ArrayList<>();
    ThaynhotAdapter adapter;
    Thaynhot ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lichsu_thaynhot);

        rvThaynhot = findViewById(R.id.rvThaynhot);
        getData();

        rvThaynhot.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ThaynhotAdapter(this, lstThaynhot);
        rvThaynhot.setAdapter(adapter);
    }

    private void getData() {
        lstThaynhot = new ArrayList<>();
        _myRef = mDatabase.getReference("Thaynhot");
        _myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Thaynhot ls = snapshot.getValue(Thaynhot.class);
                if (ls != null) {
                    lstThaynhot.add(ls);
                    String key = snapshot.getKey();
                    mKeys.add(key);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Thaynhot ls = snapshot.getValue(Thaynhot.class);
                if (ls == null || lstThaynhot == null || lstThaynhot.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                lstThaynhot.set(index, ls);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Thaynhot ls = snapshot.getValue(Thaynhot.class);
                if (ls == null || lstThaynhot == null || lstThaynhot.isEmpty())
                    return;
                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                if (index != -1) {
                    lstThaynhot.remove(index);
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
                Intent i = new Intent(this, AddThaynhotActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}