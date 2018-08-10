//package com.sqllib;
//
///**
// * An migration test which verifies the specific migration code for {@link MigrateV2ToV3}.
// *
// * @author Jeremy
// */
//public class MigrateV2ToV3Test extends MigrationTest {
//
//    public MigrateV2ToV3Test() {
//        super(new MigrateV2ToV3());
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
//            db = new com.example.doghome.gen.v2.DaoMaster.DevOpenHelper(null, null, null).getWritableDatabase();
//
//            // Create the data using V2 DAOs and Domain Objects.
//            com.example.doghome.gen.v2.DaoMaster daoMasterV2 = new com.example.doghome.gen.v2.DaoMaster(db);
//            com.example.doghome.gen.v2.DaoSession daoSessionV2 = daoMasterV2.newSession();
//
//            com.example.doghome.gen.v2.Dog noobV2 = new com.example.doghome.gen.v2.Dog(1L, "Noob", "Sharpei");
//            com.example.doghome.gen.v2.Dog beansV2 = new com.example.doghome.gen.v2.Dog(2L, "Beans", "Pug");
//
//            daoSessionV2.getDogDao().insertInTx(noobV2, beansV2);
//
//            assertTrue(daoSessionV2.getDogDao().loadAll().size() == 2);
//
//
//            // Apply the migration to current version of 2
//            getMigration().applyMigration(db, 2);
//
//
//            // Verify the update was successful
//            DaoMaster master = new DaoMaster(db);
//            DaoSession session = master.newSession();
//
//            DogDao dogDao = session.getDogDao();
//
//            // All records migrated successfully?
//            List<Dog> dogs = dogDao.loadAll();
//            assertTrue(dogs.size() == 2);
//
//            // Able to use DAO to modify values?
//            Dog noob = dogDao.load(1L);
//            Dog beans = dogDao.load(2L);
//
//            noob.setWeight(35);
//            beans.setWeight(8);
//
//            dogDao.updateInTx(noob, beans);
//
//            assertTrue(dogDao.load(1L).getWeight().equals(35));
//            assertTrue(dogDao.load(2L).getWeight().equals(8));
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//        }
//    }
//}