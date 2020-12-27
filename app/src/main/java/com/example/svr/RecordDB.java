package com.example.svr;
import android.database.Cursor;
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
    public void deleteRecord(String date){
        DB.execSQL("DELETE FROM record WHERE date = '" + date + "';");
    }
    public void bookmarkRecord(String date) {
        int bookmark = 0;
        String sql = "select * from record where date ='" + date + "'";
        Cursor cursor = RecordDB.getInstance().DB.rawQuery(sql, null);
        int index = cursor.getColumnIndex("bookmark");
        while (cursor.moveToNext()) {
            int tmp = cursor.getInt(4);
            if (tmp == 1)
                bookmark = 0;
            else
                bookmark = 1;
            DB.execSQL("update record set bookmark ="+bookmark+" where date ='" + date + "';");
        }
        cursor.close();
    }
    public void insertRecord(String name,String date,String length,int bookmark,String path) {
       String sql = "'"+name+"'"+","+"'"+date+"'"+","+"'"+length+"'"+","+bookmark+","+"'"+path+"'";
       System.out.println("SQL : "+sql);
       DB.execSQL("insert into record(name,date,length,bookmark,path) values("+sql+");");
    }
    private void createTable(String name) {
        DB.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement,"
                + "name text,"
                + "date text,"
                + "length text,"
                + "bookmark text," //boolean 타입이 없다구..?
                + "path text);");
        tableCreated = true;
    }
}
