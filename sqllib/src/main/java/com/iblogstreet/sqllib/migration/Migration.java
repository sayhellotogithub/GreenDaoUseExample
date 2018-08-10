package com.iblogstreet.sqllib.migration;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

public interface Migration {
 
    /**
     * Apply the migration to the given database
     *
     * @param db
     *            to be updated
     * @param currentVersion
     *            the current version before migration
     * @return the version after migration has been applied
     */
    int applyMigration(SQLiteDatabase db, int currentVersion);
 
    /**
     * @return instance of the previous Migration required if the current
     *         version is to old for this migration. NB: This will only be null
     *         if this is the tip of the chain and there are no other earlier
     *         migrations.
     */
    @Nullable
    Migration getPreviousMigration();
 
    /**
     * @return the target (old) version which will be migrated from.
     */
    int getTargetVersion();
 
    /**
     * @return the new version which will result from the migration being
     *         applied.
     */
    int getMigratedVersion();
}