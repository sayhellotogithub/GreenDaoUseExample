package com.sqllib;

import com.iblogstreet.sqllib.migration.Migration;

import junit.framework.TestCase;

abstract class MigrationTest extends TestCase {

    private final Migration migration;

    /**
     * Constructor
     *
     * @param migration the migration being tested
     */
    protected MigrationTest( Migration migration) {
        this.migration = migration;
    }

    /**
     * @return the Migration being tested
     */
    public Migration getMigration() {
        return migration;
    }

    /**
     * A Test which ensures the target version is greater than zero for the
     * migration
     */
    public void testTargetVersion_GreaterThanZero() {
        assertTrue(getMigration().getTargetVersion() > 0);
    }

    /**
     * A Test which ensures that the target version is one less than the
     * migrated version.
     */
    public void testTargetVersion_OneLessThanMigratedVersion() {
        assertTrue((getMigration().getTargetVersion() + 1) == getMigration()
                .getMigratedVersion());
    }

    /**
     * A Test which ensures that if the migration has a parent that it's
     * migrated version is equal to the target version.
     */
    public void testIfHasParent_ParentMigratedVersionEqualsTargetVersion() {
        Migration parent = getMigration().getPreviousMigration();
        if (parent != null) {
            assertTrue(parent.getMigratedVersion() == getMigration()
                    .getTargetVersion());
        }
    }

    /**
     * Abstract test method to be implemented by the concrete MigrationTest
     * intances which should test the actual migration process.
     */
    public abstract void testApplyMigration();

}