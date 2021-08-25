package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input , author_input , pages_input , des_input;
    Button update_button;
    String id , title , author , pages , des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_update);
        author_input = findViewById(R.id.author_update);
        pages_input = findViewById(R.id.pages_update);
        des_input = findViewById(R.id.description_update);
        update_button = findViewById(R.id.update_button);
        getIntentData();

        ActionBar ab = getSupportActionBar();
        ab.setTitle(title + " Update");

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper update_data=new DatabaseHelper(UpdateActivity.this);
                update_data.update(id,title_input.getText().toString(),author_input.getText().toString(),
                        pages_input.getText().toString(),des_input.getText().toString());
            }
        });

    }
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages") && getIntent().hasExtra("des")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            des = getIntent().getStringExtra("des");

            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
            des_input.setText(des);
        }else{
            Toast.makeText(this , "No Data" , Toast.LENGTH_LONG).show();
        }
    }
}