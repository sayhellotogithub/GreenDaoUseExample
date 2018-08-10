//package com.sqllib;
//
//import android.database.sqlite.SQLiteDatabase;
//
//import com.iblogstreet.sqllib.greendao.v2.User;
//import com.iblogstreet.sqllib.migration.MigrateV1ToV2;
//
//import java.util.List;
//
//public class MigrateV1ToV2Test extends MigrationTest {
//
//    public MigrateV1ToV2Test() {
//        super(new MigrateV1ToV2());
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void testApplyMigration() {
//        SQLiteDatabase db = null;
//
//        try {
//            db = new com.iblogstreet.sqllib.greendao.DaoMaster.DevOpenHelper(null, null, null).getWritableDatabase();
//
//            // Create the data using V1 DAOs and Domain Objects.
//            com.iblogstreet.sqllib.greendao.DaoMaster daoMasterV1 = new com.iblogstreet.sqllib.greendao.DaoMaster(db);
//            com.iblogstreet.sqllib.greendao.DaoSession daoSessionV1 = daoMasterV1.newSession();
//
//            com.iblogstreet.sqllib.entitiy.User noobV1 = new com.iblogstreet.sqllib.entitiy.User(1L, "Noob");
//            com.iblogstreet.sqllib.entitiy.User beansV1 = new com.iblogstreet.sqllib.entitiy.User(2L, "Beans");
//
//            daoSessionV1.getUserDao().insertInTx(noobV1, beansV1);
//
//            assertTrue(daoSessionV1.getUserDao().loadAll().size() == 2);
//
//
//            // Apply the migration to current version of 1
//            getMigration().applyMigration(db, 1);
//
//
//            // Verify the update was successful
//            com.iblogstreet.sqllib.greendao.v2.DaoMaster master = new com.iblogstreet.sqllib.greendao.v2.DaoMaster(db);
//            com.iblogstreet.sqllib.greendao.v2.DaoSession session = master.newSession();
//
//            com.iblogstreet.sqllib.greendao.v2.UserDao dogDao = session.getUserDao();
//
//            // All records migrated successfully?
//            List<User> dogs = dogDao.loadAll();
//            assertTrue(dogs.size() == 2);
//
//            // Default value was applied?
//            for (com.iblogstreet.sqllib.greendao.v2.User dog : dogs) {
//                assertTrue(dog.getAddress().equals("Unknown"));
//            }
//
//            // Able to use DAO to modify values?
//            com.iblogstreet.sqllib.greendao.v2.User noob = dogDao.load(1L);
//            com.iblogstreet.sqllib.greendao.v2.User beans = dogDao.load(2L);
//
//            noob.setAddress("Sharpei");
//            beans.setAddress("Pug");
//
//            dogDao.updateInTx(noob, beans);
//
//            assertTrue(dogDao.load(1L).getAddress().equals("Sharpei"));
//            assertTrue(dogDao.load(2L).getAddress().equals("Pug"));
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//        }
//    }
//}