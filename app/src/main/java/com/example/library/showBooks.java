package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class showBooks extends AppCompatActivity {
    DatabaseHelper database;
    ArrayList<String> book_id , book_title , book_author ,  book_pages , book_des;
    RecyclerView recyclerView;
    CustomAdapter customeadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(showBooks.this));
        database = new DatabaseHelper(showBooks.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_des = new ArrayList<>();
        saveBooksData();
        customeadapter = new CustomAdapter(this,book_id,book_title,book_author,book_pages,book_des);
        recyclerView.setAdapter(customeadapter);



    }
    void saveBooksData(){
        Cursor cursor = database.showBooks();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data" , Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_pages.add(cursor.getString(2));
                book_author.add(cursor.getString(3));
                book_des.add(cursor.getString(4));
            }
        }

    }
}