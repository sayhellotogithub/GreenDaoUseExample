package com.iblogstreet.sqllib.utils;

import android.content.Context;

import com.iblogstreet.sqllib.entitiy.User;
import com.iblogstreet.sqllib.greendao.UserDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * @author Armyone
 * @date 2018/8/10 13:33
 * @desc
 */

public class UserDaoUtil {
    DaoManager mDaoManager;

    public UserDaoUtil(Context context) {
        this.mDaoManager = DaoManager.getInstance();
        this.mDaoManager.init(context);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        long insert = mDaoManager.getDaoSession().insertOrReplace(user);
        return insert > 0;
    }

    /**
     * 插入用户列表
     *
     * @param userList
     * @return
     */
    public boolean insertUserList(final List<User> userList) {
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (User user : userList) {
                        mDaoManager.getDaoSession().insertOrReplace(user);
                    }
                }
            });
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    /**
     * 刷新指定数据
     */
    public boolean reFreshUser(User user) {
        boolean flag = false;
        mDaoManager.getDaoSession().refresh(user);
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @return
     */
    public boolean updateUser(User user) {
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().update(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @return
     */
    public boolean deleteUser(User user) {
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().delete(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().deleteAll(User.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public User queryUserById(long key) {
        return mDaoManager.getDaoSession().load(User.class, key);
    }

    /**
     * 使用 sql语句进行查询操作
     * 参数一sql语句  参数二查询条件限定
     */
    public List<User> queryUserBySql(String sql, String[] conditions) {
        return mDaoManager.getDaoSession().queryRaw(User.class, sql, conditions);
    }
    public List<User> queryAll(){
        return mDaoManager.getDaoSession().getUserDao().loadAll();
    }

    /**
     * 使用queryBuilder进行查询
     *
     * @return
     */
    public List<User> queryFruitByQueryBuilder(long id) {
        QueryBuilder<User> queryBuilder = mDaoManager.getDaoSession().queryBuilder(User.class);
        return queryBuilder.where(UserDao.Properties.Id.eq(id)).limit(10).offset(10).list();
    }

    public List<User> queryUserPageByUserName(int offset, int limit, String userName) {
        QueryBuilder<User> userQueryBuilder = mDaoManager.getDaoSession().queryBuilder(User.class);
        return userQueryBuilder.where(UserDao.Properties.Username.like("%"+userName+"%")).limit(limit).offset(offset).list();
    }

    /**
     * 分页条件查询实体集合
     *
     * @param entityClass
     * @param offset
     * @param limit
     * @param where
     * @param selectionArgs
     * @param <T>
     * @return
     */
    public <T> List<T> queryPage(Class<T> entityClass, int offset, int limit, String where,
                                 Property properties, Object... selectionArgs) {
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) mDaoManager.getDaoSession().getDao(entityClass);
        QueryBuilder<T> builder = dao.queryBuilder()
                .orderDesc(properties)
                .where(
                        new WhereCondition.StringCondition(where, selectionArgs))
                .offset(offset * limit)
                .limit(limit);
        return builder.list();
    }

}
