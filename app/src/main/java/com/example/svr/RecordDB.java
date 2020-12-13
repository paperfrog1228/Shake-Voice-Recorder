package com.example.svr;
import android.database.sqlite.SQLiteDatabase;
public class RecordDB {
    private static RecordDB instance;
    public static RecordDB getInstance(){
        if(instance == null){
            instance = new RecordDB();
        }
        return instance;
    }
    public SQLiteDatabase DB=null;
    boolean databaseCreated=false;
    boolean tableCreated=false;
    public void initDB(MainActivity mainActivity){
        if(!databaseCreated) {
            mainActivity.createDatabase();
            databaseCreated = true;
        }
        if(!tableCreated) {
            createTable("record");
            tableCreated = true;
        }
    }
    public boolean isDatabaseCreated(){
        if(databaseCreated) return true;
        return false;
    }
    public void insertRecord(String name,String date,String path) {
        //PhoneBook.execSQL( "insert into phone(resID,name,mobile,age) values("+resId+","+"'"+name+"'"+","+"'"+mobile+"'"+","+age+");");
        DB.execSQL("insert into record(name,date,path) values("+"'"+name+"'"+","+"'"+date+"'"+","+"'"+path+"');");
    }
    private void createTable(String name) {
        DB.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement,"
                + "name text,"
                + "date text,"
                + "path text);");
        tableCreated = true;
    }
}
