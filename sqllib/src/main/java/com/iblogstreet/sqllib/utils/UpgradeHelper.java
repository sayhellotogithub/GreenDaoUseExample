package com.iblogstreet.sqllib.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iblogstreet.sqllib.greendao.DaoMaster;
import com.iblogstreet.sqllib.greendao.StudentDao;
import com.iblogstreet.sqllib.greendao.UserDao;

import org.greenrobot.greendao.database.Database;

public class UpgradeHelper extends DaoMaster.OpenHelper {

    public UpgradeHelper(Context context, String name) {
        super(context, name);
    }

    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * Apply the appropriate migrations to update the database.
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        MigrationHelper.getInstance().migrate(db, UserDao.class, StudentDao.class);
    }
}