package com.iblogstreet.sqllib.migration;

import android.database.sqlite.SQLiteDatabase;

/**
 * Migration from Version1 to Version2
 *
 * @author Jeremy
 */
public class MigrateV1ToV2 extends MigrationImpl {
 
    /**
     * {@inheritDoc}
     */
    @Override
    public int applyMigration( SQLiteDatabase db,
            int currentVersion) {
        prepareMigration(db, currentVersion);
 
        // TODO : Apply required database update
         
        return getMigratedVersion();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public int getTargetVersion() {
        return 1;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public int getMigratedVersion() {
        return 2;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public Migration getPreviousMigration() {
        return null;
    }
}