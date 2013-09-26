package com.shangkang.helper;

import android.content.Context;
import android.util.Log;

import com.shangkang.android.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzh on 13-9-24.
 */
public class CacheHelper {

    private static Map<String, SoftReference<Object>> cache;

    public CacheHelper()
    {
        cache = new HashMap<String, SoftReference<Object>>();
    }

    public static CacheHelper getSingleton()
    {
        return SingletonContainer.singleton;
    }

    private static class SingletonContainer {
        private static CacheHelper	singleton	= new CacheHelper();
    }

    public Map<String, SoftReference<Object>> getCache()
    {
        return cache;
    }

    /*public static void cacheData(File dir, String key, Object data)
    {
        if(dir == null || key == null || data == null)
            return;

        File file = new File(MainActivity.this.getCacheDir(), key);
        FileOutputStream   fos  = null;
        ObjectOutputStream oos  = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
        } catch (Exception e) {
            Log.d("CacheHelper", "cache data error!");
        } finally {
        try {
            if (oos != null)   oos.close();
            if (fos != null)   fos.close();
        } catch (Exception e) { *//* do nothing *//* }
    }

    }*/
}
