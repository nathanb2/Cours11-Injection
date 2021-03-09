package com.example.cours11_injection.injection;

import android.content.Context;

import com.example.cours11_injection.data_base.DataBase;
import com.example.cours11_injection.repository.UserRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * class qui cree
 */
public class Injection {

    public static UserRepository provideUserRepository(Context context) {
        DataBase database = DataBase.getDatabase(context);
        return new UserRepository(database.userDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserRepository userRepository = provideUserRepository(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(userRepository, executor);
    }
}