package com.example.a51900035_51900087_51900593.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51900035_51900087_51900593.Model.Lichsu;
import com.example.a51900035_51900087_51900593.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ViewLichsuDoxangActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    TextView tvNoithuchien, tvThoigian, tvChiphi;
    Lichsu ls;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lichsu_doxang);

        inItUI();
        getData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workbook wb=new HSSFWorkbook();
                Cell cell=null;
                CellStyle cellStyle=wb.createCellStyle();
                cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);

                Sheet sheet=null;
                sheet = wb.createSheet("sheet1");

                Row row =sheet.createRow(0);
                cell=row.createCell(0);
                cell.setCellValue(tvNoithuchien.getText().toString());
                cell.setCellStyle(cellStyle);

                cell=row.createCell(1);
                cell.setCellValue(tvThoigian.getText().toString());
                cell.setCellStyle(cellStyle);

                cell=row.createCell(2);
                cell.setCellValue(tvChiphi.getText().toString());
                cell.setCellStyle(cellStyle);

                sheet.setColumnWidth(0,(10*200));
                sheet.setColumnWidth(1,(10*200));

                File file = new File(getExternalFilesDir(null),"lichsu.xls");
                FileOutputStream outputStream =null;

                try {
                    outputStream =new FileOutputStream(file);
                    wb.write(outputStream);
                    Toast.makeText(getApplicationContext(),"Xuất file thành công",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(),"Xuất file không thành công",Toast.LENGTH_LONG).show();
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
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
        button = findViewById(R.id.button);
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
                        _myRef = mDatabase.getReference("Doxang");
                        _myRef.child(ls.getId()).removeValue();
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void onClickUpdate(Lichsu ls) {
        Intent i = new Intent(this, AddDoxangActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ob_doxang", (Serializable) ls);
        i.putExtras(bundle);
        startActivity(i);
    }


}