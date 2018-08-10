package com.iblogstreet.sqllib.utils;

import android.content.Context;

import java.io.File;

/**
 * @author Armyone
 * @date 2018/8/9 17:09
 * @desc
 */

public class AppPathUtil {
    /**
     * 数据保存的地址
     *
     * @return
     */
    public static File getDbCacheDir(Context context) {
        File file = new File(context.getExternalCacheDir(), "/custdb");
        if (!file.exists())
            file.mkdir();
        return file;
    }
}
