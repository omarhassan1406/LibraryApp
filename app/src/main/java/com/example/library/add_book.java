package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        final EditText Title_input = findViewById(R.id.title_input) ;
        final EditText Author_input = findViewById(R.id.Author_input) ;
        final EditText pages_input = findViewById(R.id.pages_input);
        final EditText des_input = findViewById(R.id.description_input) ;
        Button add_button = findViewById(R.id.add_button) ;
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(add_book.this);
                myDB.add_book(Title_input.getText().toString().trim(),
                        Author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()),
                        des_input.getText().toString().trim());
            }
        });
    }
}