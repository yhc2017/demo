package com.example.litepaltest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(570);
                book.setPress("中国工信出版社");
                book.setPrice(79.00);
                book.save();
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book book = new Book();
//                book.setName("java学习");
//                book.setAuthor("某某");
//                book.setPrice(19.95);
//                book.setPages(100);
//                book.setPress("Unknow");
//                book.save();
//                book.setPrice(10.99);
//                book.save();
                Book book1 = new Book();
                book1.setPrice(14.95);
                book1.setPress("Anchor");
                book1.updateAll("name = ? and author = ?","第一行代码","郭霖");
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price < ?","15");
            }
        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book: books){
                    Log.d("MainActivity","书的名字是：" + book.getName());
                    Log.d("MainActivity","书的作者是：" + book.getAuthor());
                    Log.d("MainActivity","书的页数是：" + book.getPages());
                    Log.d("MainActivity","书的价格是：" + book.getPrice());
                    Log.d("MainActivity","书的出版社是：" + book.getPress());
                }
            }
        });
    }
}
