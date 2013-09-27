package com.shangkang.helper;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by liuzh on 13-9-26.
 */
public class CacheDataHelper {

    private static final String ROOT_RECORDER = "DB_RECORDER";
    private static final String MESSAGE_EXCEPTION = "CacheDataHelper is not initialized";

    private static final CacheDataHelper instance = new CacheDataHelper();

    public static CacheDataHelper getInstance() {
        return instance;
    }

    private Context mContext;
    private CacheData recorder;

    private CacheDataHelper() {
    }

    public void init(Context context) {
        if (!this.isInitialized() && context != null) {
            this.mContext = context;
            this.recorder = new CacheData(this.mContext, getDbName(this.mContext));
        }
    }

    public boolean isInitialized() {
        return (this.recorder != null);
    }

    public void put(String key, Object value) throws RuntimeException {
        if (!this.isInitialized()) {
            throw new RuntimeException(MESSAGE_EXCEPTION);
        }

        if (value != null) {
            this.recorder.put(key, value);
        } else {
            this.delete(key);
        }
    }

    public void putBitmap(String key, Bitmap value)
    {
        if (!this.isInitialized()) {
            throw new RuntimeException(MESSAGE_EXCEPTION);
        }

        if (value != null) {
            this.recorder.putBitmap(key, value);
        } else {
            this.delete(key);
        }
    }

    public Bitmap getBitmap(String key)
    {
        return this.isInitialized() ? this.recorder.getBitmap(key) : null;
    }

    public void putBytes(String key, byte[] data) throws RuntimeException {
        if (!this.isInitialized()) {
            throw new RuntimeException(MESSAGE_EXCEPTION);
        }

        if (data != null) {
            this.recorder.putBytes(key, data);
        } else {
            this.delete(key);
        }
    }

    public byte[] getBytes(String key) {
        return this.isInitialized() ? this.recorder.getBytes(key) : new byte[0];
    }

    public Object get(String key) {
        return this.isInitialized() ? this.recorder.get(key) : null;
    }

    public String getString(String key) {
        Object data = this.get(key);
        if (data instanceof String) {
            return (String) data;
        }
        return null;
    }

    public void delete(String key) {
        if (this.isInitialized()) {
            this.recorder.removeByKey(key);
        }
    }

    public void clear() {
        if (this.recorder != null) {
            this.recorder.removeAll();
        }
    }

    private static String getDbName(Context context) {
        if (context == null) {
            return ROOT_RECORDER;
        } else {
            return new StringBuilder(ROOT_RECORDER)
                    .append('_')
                    .append(context.getApplicationInfo().loadLabel(context.getPackageManager()))
                    .toString().trim();
        }
    }
}
