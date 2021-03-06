package com.example.docta.myapplication.Classes.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.docta.myapplication.Classes.util.Teacher;
import java.util.ArrayList;

public class TeacherDAO implements DatabaseConstants{

    private DatabaseController controller;
    private SQLiteDatabase database;

    public TeacherDAO(Context context){
        controller = DatabaseController.getInstance(context);
    }

    public void open(){
        try{
            database=controller.getWritableDatabase();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertTeacherAccountsInDatabase(ArrayList<Teacher> list) {
        database.beginTransaction();
        try{
            SQLiteStatement insert = database.compileStatement(INSERT_TEACHER);
            for (int i = 0; i < list.size(); i++) {
                insert.bindString(1,list.get(i).getEmail());
                insert.bindString(2,list.get(i).getPassword());

                insert.executeInsert();
            }
            database.setTransactionSuccessful();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            database.endTransaction();
        }
    }

    public boolean LoginTeacherFromDatabase(String email, String password){
        Cursor c = database.rawQuery(QUERRY_LOGIN_TEACHER,new String[]{email,password});
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public long UpdateTeacherAccount(String newPass,String email){

        ContentValues contentValues = new ContentValues();
        contentValues.put(TEACHER_COLUMN_PASSWORD, newPass);


        return  database.update(TEACHER_TABLE_NAME,contentValues,TEACHER_COLUMN_EMAIL + " = ?",new String[]{email});
    }

    public int DeleteTeacherAccount(String email){
        return database.delete(TEACHER_TABLE_NAME,TEACHER_COLUMN_EMAIL+" =?", new String[]{email});
    }


    public void close(){
        try{
            database.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
