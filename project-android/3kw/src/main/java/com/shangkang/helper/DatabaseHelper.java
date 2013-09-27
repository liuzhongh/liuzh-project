package com.shangkang.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuzh on 13-9-26.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String SQL_DB_TABLE_NAME  = "T_CACHE";
    static final String SQL_DB_FIELD_ID  = "_ID";
    static final String SQL_DB_FIELD_KEY  = "_CACHE_KEY";
    static final String SQL_DB_FIELD_DATA  = "_CACHE_DATA";

    private static final int DEFAULT_DATABASE_VERSION = 1;

    private static final String SQL_DB_CREATE_TABLE = new StringBuilder( "CREATE TABLE " )
            .append( SQL_DB_TABLE_NAME ).append( '(' )
            .append( SQL_DB_FIELD_ID )
            .append( " INTEGER PRIMARY KEY AUTOINCREMENT, " )
            .append( SQL_DB_FIELD_KEY )
            .append( " TEXT, " )
            .append( SQL_DB_FIELD_DATA )
            .append( " BLOB);" )
            .toString( );

    private static final String SQL_DB_DROP_TABLE = "DROP TABLE IF EXISTS " + SQL_DB_TABLE_NAME;

    DatabaseHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version )
    {
        super( context, name, factory, version );
    }

    DatabaseHelper( Context context, String name )
    {
        super( context, name, null, DEFAULT_DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( SQL_DB_CREATE_TABLE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL( SQL_DB_DROP_TABLE );
        this.onCreate( db );
    }
}
