package com.iblogstreet.sqllib.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;

/**
 * @author Army
 * @date 2018/8/9 17:04
 * @desc 默认情况下，GreenDao数据库存储在/data/data/package/databases 目录下
 * 如果要指定数据库保存的位置，需要重写ContextWrapper
 * 调用方式
 * DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(), "cust.db", null);
 */
public class GreenDaoContext extends ContextWrapper {
    public GreenDaoContext(Context context) {
        super(context);
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        File baseFile = AppPathUtil.getDbCacheDir(getApplicationContext());
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        buffer.append("/");
        buffer.append(dbName);
        Log.e("GreenDao", "buffer.toString()" + buffer.toString());
        return new File(buffer.toString());
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
     * android.database.sqlite.SQLiteDatabase.CursorFactory,
     * android.database.DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

}
