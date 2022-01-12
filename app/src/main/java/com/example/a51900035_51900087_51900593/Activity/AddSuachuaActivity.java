package com.example.a51900035_51900087_51900593.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a51900035_51900087_51900593.Model.Suachua;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSuachuaActivity extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    EditText edtNoithuchien, edtThoigian, edtChiphi, edtLinhkien;
    Button btnSave;
    Suachua ls;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suachua);

        inIt();
        getData();
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(ls);
                finish();
            }
        });
    }

    private void setData(Suachua ls) {
        if(ls == null){
            ls = new Suachua();
        }
        ls.setNoithuchien(edtNoithuchien.getText().toString());
        ls.setThoigian(edtThoigian.getText().toString());
        ls.setChiphi(edtChiphi.getText().toString());
        ls.setLinhkien(edtLinhkien.getText().toString());
        ls.setPic(R.drawable.ic_baseline_history_24);

        _myRef = mDatabase.getReference("Suachua");
        if(!flag) { //add
            String id = _myRef.push().getKey();
            ls.setId(id);
            _myRef.child(id).setValue(ls);
        }
        else { // edit
            _myRef.child(ls.getId()).setValue(ls);
            Intent i =new Intent(this, ViewLichsuSuachuaActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_suachua", ls);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle==null) { return; //flag=false, add
        }
        flag=true ; //edit
        ls = (Suachua) bundle.get("object_suachua");
        edtNoithuchien.setText(ls.getNoithuchien());
        edtThoigian.setText(ls.getThoigian());
        edtChiphi.setText(ls.getChiphi());
        edtLinhkien.setText(ls.getLinhkien());
    }
    private void inIt() {
        edtNoithuchien = findViewById(R.id.edtNoithuchien);
        edtThoigian = findViewById(R.id.edtThoigian);
        edtChiphi = findViewById(R.id.edtChiphi);
        edtLinhkien = findViewById(R.id.edtLinhkien);
    }

}