package com.bb.offerapp2.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by bb on 2017/4/26.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {


    public static final String CREATE_USER = "create table usertb ("
            + "id integer primary key autoincrement, "
            + "name text not null, "
            + "age integer not null, "
            + "sex text not null)";

    private Context mContext;


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL("insert into usertb(name,sex,age) values('张三','女',18)");
        db.execSQL("insert into usertb(name,sex,age) values('李四','男',19)");
        db.execSQL("insert into usertb(name,sex,age) values('王五','女',22)");
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usertb");
        onCreate(db);
    }
}
