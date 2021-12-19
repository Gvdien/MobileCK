package com.example.a51900035_51900087_51900593.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a51900035_51900087_51900593.Model.Lichsu;
import com.example.a51900035_51900087_51900593.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDoxangActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    EditText edtNoithuchien, edtThoigian, edtChiphi;
    Button btnSave;
    Lichsu ls;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doxang);

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

    private void setData(Lichsu ls) {
        if(ls == null){
            ls = new Lichsu();
        }
        String noithuchien = edtNoithuchien.getText().toString();
        String thoigian = edtThoigian.getText().toString();
        int chiphi = Integer.parseInt(String.valueOf(edtChiphi.getText()));
        ls.setNoithuchien(noithuchien);
        ls.setThoigian(thoigian);
        ls.setChiphi(chiphi);
        ls.setPic(R.drawable.ic_baseline_history_24);

        _myRef = mDatabase.getReference("Doxang");
        if(!flag) { //add
            String id = _myRef.push().getKey();
            ls.setId(id);
            _myRef.child(id).setValue(ls);
        }
        else { // edit
            _myRef.child(ls.getId()).setValue(ls);
            Intent i =new Intent(this, ViewLichsuDoxangActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ob_doxang", ls);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle==null) { return; //flag=false, add
        }
        flag=true ; //edit
        ls = (Lichsu) bundle.get("ob_doxang");
        edtNoithuchien.setText(ls.getNoithuchien());
        edtThoigian.setText(ls.getThoigian());
        edtChiphi.setText(ls.getChiphi());
    }

    private void inIt() {
        edtNoithuchien = findViewById(R.id.edtNoithuchien);
        edtThoigian = findViewById(R.id.edtThoigian);
        edtChiphi = findViewById(R.id.edtChiphi);
    }
}