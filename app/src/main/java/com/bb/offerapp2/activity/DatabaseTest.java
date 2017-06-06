package com.bb.offerapp2.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bb.offerapp2.R;
import com.bb.offerapp2.util.BaseActivity;

public class DatabaseTest extends BaseActivity {

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private TextView tv_id, tv_name, tv_sex, tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);

        // 创建一个数据库并且打开，如果存在就打开
        dbHelper = new MyDatabaseHelper(this, "user.db", null, 1);
        dbHelper.getWritableDatabase();

        //查询
        query(findViewById(R.id.query));




        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

    }


    public void query(View view) {
        tv_id.setText("");
        tv_name.setText("");
        tv_sex.setText("");
        tv_age.setText("");
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usertb", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                tv_id.append("\n" + cursor.getString(cursor.getColumnIndex("id")));
                tv_name.append("\n" + cursor.getString(cursor.getColumnIndex("name")));
                tv_sex.append("\n" + cursor.getString(cursor.getColumnIndex("sex")));
                tv_age.append("\n" + cursor.getString(cursor.getColumnIndex("age")));
            }
            cursor.close();
        }
        //db.close();不要关闭，不然调用其他函数时db已被关闭，在onDestroy中关  避免内存泄漏
    }


    public void add(View view) {
        ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据
        cv.put("name", "新来的");
        cv.put("sex", "女");
        cv.put("age", "18");
        db.insert("usertb", null, cv);//执行插入操作
        query(findViewById(R.id.query));
    }

    public void delete(View view) {
        db.delete("usertb", "name = ?", new String[]{"新来的"});
//        String sql = "delete from usertb where name='新来的'";//删除操作的SQL语句
//        db.execSQL(sql);//执行删除操作
        query(findViewById(R.id.query));
    }


    public void update(View view) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("name", "张三三");//添加要更改的字段及内容
        db.update("usertb", cv, "name = ?", new String[]{"张三"});//执行修改
        query(findViewById(R.id.query));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db !=null){
            db.close();
        }

    }
}
