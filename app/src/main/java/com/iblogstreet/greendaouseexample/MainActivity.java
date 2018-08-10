package com.iblogstreet.greendaouseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iblogstreet.sqllib.entitiy.User;
import com.iblogstreet.sqllib.utils.UserDaoUtil;

import java.util.List;

import com.iblogstreet.greendaouseexample.permission.BasePermissionListener;
import com.iblogstreet.greendaouseexample.permission.PermissionUtil;

public class MainActivity extends AppCompatActivity {
    UserDaoUtil userDaoUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.requestPermission(this, new BasePermissionListener() {

                    @Override
                    public void onGranted() {
                        userDaoUtil = new UserDaoUtil(MainActivity.this);
                    }

                    @Override
                    public void onCancel() {
                        super.onCancel();
                        finish();
                    }

                    @Override
                    public void onToSetting() {
                        super.onToSetting();
                        finish();
                    }
                },
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE);
    }


    int i = 0;

    public void addUser(View view) {
        userDaoUtil.insertUser(new User(null, "test" + i,null));
        i++;
    }

    public void deleteUser(View view) {
        userDaoUtil.deleteUser(new User(1l, "",null));
    }

    public void updateUser(View view) {
        userDaoUtil.deleteUser(new User(2l, "tes2",null));
    }

    public void queryUserByPage(View view) {
        List<User> userList =userDaoUtil.queryUserPageByUserName(0, 10, "test");
        if (null != userList) {
            for (User user : userList) {
                System.out.println(user.getUsername() + ":" + user.getId());
            }
        }

    }
}
