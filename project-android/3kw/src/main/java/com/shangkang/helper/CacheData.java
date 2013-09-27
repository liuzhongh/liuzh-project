package com.shangkang.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzh on 13-9-26.
 */
public class CacheData {
    private static final String TAG = CacheData.class.getName();

    private static final String DATABASE_FILENAME_PREFIX = "Data_";
    private static final String DATABASE_FILENAME_SUFFIX = ".db";

    private static final char CHAR_DOUBLE_QUOTE = '\"';

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String dbFileName;
    private Context mContext;

    CacheData(Context context, String name) {
        this.mContext = context;
        this.dbFileName = new StringBuilder(DATABASE_FILENAME_PREFIX)
                .append(name)
                .append(DATABASE_FILENAME_SUFFIX)
                .toString().trim();
    }

    private void open() throws SQLiteException {
        this.dbHelper = new DatabaseHelper(this.mContext, this.dbFileName);
        this.database = this.dbHelper.getWritableDatabase();
    }

    private void close() {
        this.dbHelper.close();
    }

    private synchronized void save(String key, byte[] value) {
        try {
            this.open();

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.SQL_DB_FIELD_KEY, key);
            values.put(DatabaseHelper.SQL_DB_FIELD_DATA, value);

            int rowsUpdated = this.database.update(DatabaseHelper.SQL_DB_TABLE_NAME,
                    values,
                    createSelectionStatement(key),
                    null);
            if (rowsUpdated == 0) {
                this.database.insert(DatabaseHelper.SQL_DB_TABLE_NAME, null, values);
            }
        } catch (Exception ex) {
            Log.w(TAG, ex);
        } finally {
            this.close();
        }
    }

    private synchronized byte[] load(String key) {
        byte[] data = new byte[0];
        Cursor cursor = null;
        try {
            this.open();
            cursor = this.database.query(DatabaseHelper.SQL_DB_TABLE_NAME,
                    new String[]{DatabaseHelper.SQL_DB_FIELD_DATA},
                    createSelectionStatement(key),
                    null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                data = cursor.getBlob(0);
            }
        } catch (Exception ex) {
            Log.w(TAG, ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            this.close();
        }
        return data;
    }

    void put(String key, Object value) {
        if (key != null) {
            if (value != null) {
                byte[] data = serializeObject(value);
                if (data != null) {
                    this.save(key, data);
                }
            } else {
                this.removeByKey(key);
            }
        }
    }

    Bitmap getBitmap(String key) {
        if (key == null)
            return null;

        byte[] bt = this.load(key);

        if (bt != null)
            return BitmapFactory.decodeByteArray(bt, 0, bt.length);

        return null;
    }

    void putBitmap(String key, Bitmap value) {
        if (key != null) {
            if (value != null) {
                byte[] data = readBitmap(value);
                if (data != null) {
                    this.save(key, data);
                }
            } else {
                this.removeByKey(key);
            }
        }
    }

    private byte[] readBitmap(Bitmap value) {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream(value.getWidth() * value.getHeight() * 4);
            value.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
            }

        }
        return null;
    }

    void putBytes(String key, byte[] data) {
        if (key != null) {
            if (data != null) {
                this.save(key, data);
            } else {
                this.removeByKey(key);
            }
        }
    }

    byte[] getBytes(String key) {
        return (key != null) ? this.load(key) : new byte[0];
    }

    Object get(String key) {
        return (key != null) ? deserializeObject(this.load(key)) : null;
    }

    int getRecordSize(String key) {
        if (key != null && key.length() > 0) {
            return this.load(key).length;
        }
        return 0;
    }

    synchronized List<String> getKeys() {
        List<String> keys = new ArrayList<String>();
        Cursor cursor = null;
        try {
            this.open();

            cursor = this.database.query(
                    DatabaseHelper.SQL_DB_FIELD_KEY,
                    new String[]{DatabaseHelper.SQL_DB_FIELD_KEY},
                    null, null, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                while (!cursor.isAfterLast()) {
                    String key = cursor.getString(0);
                    keys.add(key);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.w(TAG, ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            this.close();
        }
        return keys;
    }

    synchronized void removeByKey(String key) {
        if (key != null) {
            try {
                this.open();
                this.database.delete(DatabaseHelper.SQL_DB_TABLE_NAME,
                        createSelectionStatement(key),
                        null);
            } catch (Exception sqlEx) {
                Log.w(TAG, sqlEx);
            } finally {
                this.close();
            }
        }
    }

    synchronized void removeAll() {
        try {
            this.open();
            this.database.delete(DatabaseHelper.SQL_DB_TABLE_NAME, null, null);
        } catch (Exception sqlEx) {
            Log.w(TAG, sqlEx);
        } finally {
            this.close();
        }
    }

    private static byte[] serializeObject(Object obj) {
        byte[] byteArray = null;
        if (obj != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
            byteArray = baos.toByteArray();
        }
        return byteArray;
    }

    private static Object deserializeObject(byte[] byteArray) {
        Object obj = null;
        if (byteArray != null && byteArray.length > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
                obj = ois.readObject();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }

    private static String createSelectionStatement(String key) {
        StringBuilder buffer = new StringBuilder();
        if (key != null && key.length() > 0) {
            buffer.append(DatabaseHelper.SQL_DB_FIELD_KEY);
            buffer.append('=');
            buffer.append(CHAR_DOUBLE_QUOTE);
            buffer.append(key);
            buffer.append(CHAR_DOUBLE_QUOTE);
        }
        return buffer.toString().trim();
    }
}
