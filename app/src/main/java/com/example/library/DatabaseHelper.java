package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "Library.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_DES = "book_description";

    private static final String TABLE_NAME2 = "users";
    private static final String COLUMN_ID2 = "_id2";
    private static final String COLUMN_USERS = "username";
    private static final String COLUMN_PASSWORD = "password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE + " TEXT, "+
                COLUMN_PAGES + " INTEGER, "+
                COLUMN_AUTHOR + " TEXT, "+
                COLUMN_DES + " TEXT);";

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_USERS + " TEXT, "+
                COLUMN_PASSWORD + " TEXT);";

        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);
    }

    public void add_book(String title, String author, int pages, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put(COLUMN_TITLE , title);
        row.put(COLUMN_AUTHOR,author);
        row.put(COLUMN_PAGES,pages);
        row.put(COLUMN_DES,description);


        long add = db.insert(TABLE_NAME,null,row);

        if (add == -1){
            Toast.makeText(context,"Faild" , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Added Succefully" , Toast.LENGTH_LONG).show();
        }

    }

    public Cursor showBooks(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbase = this.getReadableDatabase();

        Cursor cursor = null;

        if (dbase != null){
            cursor = dbase.rawQuery(query, null);
        }
        return cursor;
    }

    void update(String id,String title,String author,String pages,String des)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(COLUMN_TITLE,title);
        c.put(COLUMN_AUTHOR,author);
        c.put(COLUMN_PAGES,pages);
        c.put(COLUMN_DES,des);
        long res=db.update(TABLE_NAME,c,"_id=?",new String[]{id});
        if(res==-1){Toast.makeText(context,"Failed ",Toast.LENGTH_SHORT).show();}
        else
        { Toast.makeText(context,"Successful Update",Toast.LENGTH_SHORT).show();}

    }
    void delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long res= db.delete(TABLE_NAME,"_id=?",new String[]{id});
        if(res==-1){Toast.makeText(context,"Failed ",Toast.LENGTH_SHORT).show();}
        else
        { Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();}
    }

    public Boolean insertUserData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert(TABLE_NAME2, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_NAME2+" where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLE_NAME2+" where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
