package com.example.a51900035_51900087_51900593.Activity;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a51900035_51900087_51900593.Model.Thaynhot;
import com.example.a51900035_51900087_51900593.R;
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

public class ViewLichsuThaynhotActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference _myRef;
    TextView tvNoithuchien, tvThoigian, tvChiphi, tvLoainhot;
    Thaynhot ls;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lichsu_thaynhot);
        inIt();
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
                    Toast.makeText(getApplicationContext(),"Xu???t file th??nh c??ng",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(),"Xu???t file kh??ng th??nh c??ng",Toast.LENGTH_LONG).show();
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_add_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                finish();
                onClickUpdate(ls);
                break;
            case R.id.delete:
                onClickDelete(ls);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickDelete(Thaynhot ls) {
        new AlertDialog.Builder(this)
                .setTitle("Xoa")
                .setMessage("B???n c?? ch???c ch???n mu???n x??a s???n ph???m kh??ng?")
                .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _myRef = mDatabase.getReference("Thaynhot");
                        _myRef.child(ls.getId()).removeValue();
                        finish();
                    }
                })

                .setNegativeButton("Kh??ng", null)
                .show();
    }

    private void onClickUpdate(Thaynhot ls) {
        Intent i = new Intent(this, AddThaynhotActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_thaynhot", ls);
        i.putExtras(bundle);
        startActivity(i);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;
        ls = (Thaynhot) bundle.get("object_thaynhot");
        tvNoithuchien.setText(ls.getNoithuchien());
        tvThoigian.setText(ls.getThoigian());
        tvChiphi.setText(ls.getChiphi());
        tvLoainhot.setText(ls.getLoainhot());
    }

    private void inIt() {
        tvNoithuchien = findViewById(R.id.tvNoithuchien);
        tvThoigian = findViewById(R.id.tvThoigian);
        tvChiphi = findViewById(R.id.tvChiphi);
        tvLoainhot = findViewById(R.id.tvLoainhot);
        button = findViewById(R.id.button);
    }
}