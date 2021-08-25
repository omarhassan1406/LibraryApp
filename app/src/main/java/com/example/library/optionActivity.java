package com.example.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class optionActivity extends AppCompatActivity {
    String id , title , author , pages , des;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Button see_des , update , delete;
        see_des = findViewById(R.id.des_btn);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);


        see_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(optionActivity.this , descreptionActivity.class);
                getIntentDataAndputExtra(intent);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(optionActivity.this , UpdateActivity.class);
                getIntentDataAndputExtra(intent);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getIntentData();
                confirmDialog();

            }
        });


    }
    void getIntentDataAndputExtra(Intent intent){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages") && getIntent().hasExtra("des")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            des = getIntent().getStringExtra("des");

            intent.putExtra("id",String.valueOf(id));
            intent.putExtra("title",String.valueOf(title));
            intent.putExtra("author",String.valueOf(author));
            intent.putExtra("pages",String.valueOf(pages));
            intent.putExtra("des",String.valueOf(des));
        }else{
            Toast.makeText(this , "No Data" , Toast.LENGTH_LONG).show();
        }
    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages") && getIntent().hasExtra("des")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            des = getIntent().getStringExtra("des");
        }else{
            Toast.makeText(this , "No Data" , Toast.LENGTH_LONG).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Delete " + title + " ?");
        b.setMessage("Are you sure you want to delete " + title + " ?");
        b.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(optionActivity.this);
                db.delete(id);
            }
        });
        b.setNegativeButton("No" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        b.create().show();
    }
}