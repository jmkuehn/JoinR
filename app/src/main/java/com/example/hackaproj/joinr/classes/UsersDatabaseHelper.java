package com.example.hackaproj.joinr.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class UsersDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "userstable.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_USERS = "users";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_COLSCHEME = "colscheme";
	public static final String COLUMN_FRIENDS = "friends";
	public static final String COLUMN_DEFAULTEVENT = "defaultevent";
    public static final String COLUMN_CURRENTEVENT = "currentevent";


    private HashMap hp;
	
	public UsersDatabaseHelper(Context context)
	{
	   super(context, DATABASE_NAME , null, 1);
	}
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_USERS
			+ "("
			+ COLUMN_ID +  " text not null, "
			+ COLUMN_NAME + " text not null, "
			+ COLUMN_PASSWORD + " text not null, "
			+ COLUMN_COLSCHEME + " text not null, "
			+ COLUMN_FRIENDS + " text not null, "
			+ COLUMN_DEFAULTEVENT + " text not null, "
            + COLUMN_CURRENTEVENT + " text not null, "
			+ ");";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}
	
	public boolean insertUser(String id, String name, String password, String colscheme, String friends, String defaultevent, String currentevent)
	{
	   SQLiteDatabase db = this.getWritableDatabase();
	   ContentValues contentValues = new ContentValues();

	   contentValues.put(COLUMN_ID, id);
	   contentValues.put(COLUMN_NAME, name);
	   contentValues.put(COLUMN_PASSWORD, password);
	   contentValues.put(COLUMN_COLSCHEME, colscheme);
	   contentValues.put(COLUMN_FRIENDS, friends);
	   contentValues.put(COLUMN_DEFAULTEVENT, defaultevent);
       contentValues.put(COLUMN_CURRENTEVENT, currentevent);

	   db.insert(TABLE_USERS, null, contentValues);
	   return true;
	}
	
	public Cursor getData(String id){
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor res =  db.rawQuery( "select * from " + TABLE_USERS + " where " + COLUMN_ID + "="+id+"", null );
	   return res;
	}
	
	public boolean updateUser(String id, String name, String password, String colscheme, String friends, String defaultevent, String currentevent)
	{
	   SQLiteDatabase db = this.getWritableDatabase();
	   ContentValues contentValues = new ContentValues();
	   
	   contentValues.put(COLUMN_NAME, name);
	   contentValues.put(COLUMN_PASSWORD, password);
	   contentValues.put(COLUMN_COLSCHEME, colscheme);
	   contentValues.put(COLUMN_FRIENDS, friends);
        contentValues.put(COLUMN_DEFAULTEVENT, defaultevent);
        contentValues.put(COLUMN_CURRENTEVENT, currentevent);

	   
	   db.update(TABLE_USERS, contentValues, COLUMN_ID + " = ? ", new String[] { id } );
	   return true;
	}

	public Integer deleteUser(String id)
	{
	   SQLiteDatabase db = this.getWritableDatabase();
	   return db.delete(TABLE_USERS, 
	   COLUMN_ID + " = ? ", 
	   new String[] { id });
	}
	
	public boolean clear () {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + TABLE_USERS);
		return true;
	}
}
