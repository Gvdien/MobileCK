package com.example.a51900035_51900087_51900593.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a51900035_51900087_51900593.Model.Thaynhot;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddThaynhotActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    EditText edtNoithuchien, edtThoigian, edtChiphi, edtLoainhot;
    Button btnSave;
    Thaynhot ls;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thaynhot);

        inIt();
        getData();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(ls);
            }
        });
    }
    private void setData(Thaynhot ls) {
        if(ls == null){
            ls =new Thaynhot();
        }

        ls.setNoithuchien(edtNoithuchien.getText().toString());
        ls.setThoigian(edtThoigian.getText().toString());
        ls.setChiphi(edtChiphi.getText().toString());
        ls.setLoainhot(edtLoainhot.getText().toString());
        ls.setPic(R.drawable.ic_baseline_history_24);

        _myRef = mDatabase.getReference("Thaynhot");
        if(flag == false){
            String id = _myRef.push().getKey();
            ls.setId(id);
            _myRef.child(id).setValue(ls);
        }
        else {
            _myRef.child(ls.getId()).setValue(ls);
            Intent i  = new Intent(this, ViewLichsuThaynhotActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_thaynhot", ls);
            i.putExtras(bundle);
            startActivity(i);
        }
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        flag = true;
        ls = (Thaynhot) bundle.get("object_thaynhot");
        edtNoithuchien.setText(ls.getNoithuchien());
        edtThoigian.setText(ls.getThoigian());
        edtChiphi.setText(ls.getChiphi());
        edtLoainhot.setText(ls.getLoainhot());
    }

    private void inIt() {
        edtNoithuchien = findViewById(R.id.edtNoithuchien);
        edtThoigian = findViewById(R.id.edtThoigian);
        edtChiphi = findViewById(R.id.edtChiphi);
        edtLoainhot = findViewById(R.id.edtLoainhot);
        btnSave = findViewById(R.id.btnSave);
    }
}