package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class descreptionActivity extends AppCompatActivity {
    TextView titleText , desText;
    String id , title , author , pages , des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descreption);
        titleText = findViewById(R.id.TitleforDes);
        desText = findViewById(R.id.DesforDes);
        getIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title + " Descreption");
        desText.setMovementMethod(new ScrollingMovementMethod());
    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages") && getIntent().hasExtra("des")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            des = getIntent().getStringExtra("des");

            titleText.setText(title);
            desText.setText(des);
        }else{
            Toast.makeText(this , "No Data" , Toast.LENGTH_LONG).show();
        }
    }
}