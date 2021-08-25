package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList book_id , book_title , book_author ,  book_pages , book_des;
    CustomAdapter(Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages, ArrayList book_des){
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.book_des = book_des;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , optionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
                intent.putExtra("pages",String.valueOf(book_pages.get(position)));
                intent.putExtra("des",String.valueOf(book_des.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt , book_title_txt , book_author_txt ,  book_pages_txt;
        LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title_txt = itemView.findViewById(R.id.title);
            book_author_txt = itemView.findViewById(R.id.Author);
            book_pages_txt = itemView.findViewById(R.id.pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }





    }

}
