package com.example.cours11_injection.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cours11_injection.dao.UserDao;
import com.example.cours11_injection.data_base.DataBase;
import com.example.cours11_injection.model.User;

import java.util.List;

/**
 * Voire cours-8 (on remplace juste le UserApiService simple de cours-8 par une autre interface annote pour Room qui est le UserDao)
 */
public class UserRepository {

    UserDao userDao;

    /**
     * @param userDao Le constructor recoit desormais directement ce dont il a besoin : userDao  et non plus application pour recuperer la database pour creer un Dao
     */
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(long id) {
        return userDao.getUser(id);
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    /**
     * @return userDao.getAllUsers() qui est lui meme un liveData
     */
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getMajorUsers() {
        return userDao.getMajorUsers();
    }
}
