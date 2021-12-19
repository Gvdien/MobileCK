package com.example.a51900035_51900087_51900593.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51900035_51900087_51900593.R;

public class EmergencyActivity extends AppCompatActivity {

    ImageView ivCall,ivCall1,ivCall2,ivCall3,ivMessage,ivMessage1,ivMessage2,ivMessage3;
    TextView tvPhone, tvPhone1, tvPhone2, tvPhone3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        inIt();

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone.getText().toString();
                Intent a = new Intent(Intent.ACTION_DIAL);
                a.setData(Uri.parse("tel:" + number));
                startActivity(a);
            }
        });

        ivCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone1.getText().toString();
                Intent a = new Intent(Intent.ACTION_DIAL);
                a.setData(Uri.parse("tel:" + number));
                startActivity(a);
            }
        });

        ivCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone2.getText().toString();
                Intent a = new Intent(Intent.ACTION_DIAL);
                a.setData(Uri.parse("tel:" + number));
                startActivity(a); }
        });

        ivCall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone3.getText().toString();
                Intent a = new Intent(Intent.ACTION_DIAL);
                a.setData(Uri.parse("tel:" + number));
                startActivity(a);
            }
        });

        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone.getText().toString();
                Intent a = new Intent(Intent.ACTION_SENDTO);
                a.putExtra("sms_body","Can you help me ?");
                a.setData(Uri.parse("sms:" + number));
                startActivity(a);
            }
        });

        ivMessage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone1.getText().toString();
                Intent a = new Intent(Intent.ACTION_SENDTO);
                a.putExtra("sms_body","Can you help me ?");
                a.setData(Uri.parse("sms:" + number));
                startActivity(a);
            }
        });

        ivMessage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone2.getText().toString();
                Intent a = new Intent(Intent.ACTION_SENDTO);
                a.putExtra("sms_body","Can you help me ?");
                a.setData(Uri.parse("sms:" + number));
                startActivity(a);
            }
        });

        ivMessage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tvPhone3.getText().toString();
                Intent a = new Intent(Intent.ACTION_SENDTO);
                a.putExtra("sms_body","Can you help me ?");
                a.setData(Uri.parse("sms:" + number));
                startActivity(a);
            }
        });
    }

    public void inIt(){
        ivCall = findViewById(R.id.ivCall);
        ivCall1 = findViewById(R.id.ivCall1);
        ivCall2 = findViewById(R.id.ivCall2);
        ivCall3 = findViewById(R.id.ivCall3);
        ivMessage = findViewById(R.id.ivMessage);
        ivMessage1 = findViewById(R.id.ivMessage1);
        ivMessage2 = findViewById(R.id.ivMessage2);
        ivMessage3 = findViewById(R.id.ivMessage3);
        tvPhone = findViewById(R.id.tvPhone);
        tvPhone1 = findViewById(R.id.tvPhone1);
        tvPhone2 = findViewById(R.id.tvPhone2);
        tvPhone3 = findViewById(R.id.tvPhone3);
    }
}