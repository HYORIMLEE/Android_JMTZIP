package com.example.dbtest;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button select_btn;

    DatabaseHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select_btn = (Button)findViewById(R.id.select_btn);
        helper = new DatabaseHelper(MainActivity.this, "Matzip.db", null, 1);
        DatabaseHelper databaseHelper = new DatabaseHelper(this, "DB", null, 1);
        // 쓰기 가능한 SQLiteDatabase 인스턴스 구함
        db = databaseHelper.getWritableDatabase();

        db.close();
        databaseHelper.close();
    }

    void DBSearch(String tableName){
        Cursor cursor = null;

        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("NAME"));
                    String category= cursor.getString(cursor.getColumnIndex("CATEGORY"));
                    String location = cursor.getString(cursor.getColumnIndex("LOCATION"));
                    String time = cursor.getString(cursor.getColumnIndex("TIME"));
                    String cost = cursor.getString(cursor.getColumnIndex("COST"));
                    String score = cursor.getString(cursor.getColumnIndex("SCORE"));

                    Log.d(TAG, "name: " + name + ", category: " + category + ", location: " + location + ", time: " + time + "cost: " + cost + "score: " + score);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // SELECT * FROM People WHERE age < "age" ORDER BY NAME
        void DBSearch(String tableName, Integer Double name) {
            Cursor cursor = null;

            try {
                cursor = db.query(tableName, null, "NAME" + " < ?", new String[]{name.toString()}, null, null, "NAME");

                if (cursor != null) {
                    while (cursor.moveToNext()) { // 다음 행으로
                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        String category = cursor.getString(cursor.getColumnIndex("CATEGORY"));
                        String location = cursor.getString(cursor.getColumnIndex("LOCATION"));
                        String time = cursor.getString(cursor.getColumnIndex("TIME"));
                        String cost = cursor.getString(cursor.getColumnIndex("COST"));
                        String score = cursor.getString(cursor.getColumnIndex("SCORE"));

                        Log.d(TAG, "name: " + name + ", category: " + category + ", location: " + location + ", time: " + time + "cost: " + cost + "score: " + score);
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

    }
}