package com.example.cours11_injection.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cours11_injection.model.User;
import com.example.cours11_injection.repository.UserRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class UsersViewModel extends ViewModel {
    private final Executor mExecutor;
    private UserRepository mRepository;

    public UsersViewModel(UserRepository userRepository, Executor executor) {
        mRepository = userRepository;
        mExecutor = executor;
    }

    public LiveData<List<User>> getAllUsers() {
        return mRepository.getAllUsers();
    }

    public void updateUser(User user) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mRepository.updateUser(user);
            }
        });
    }

    public void createUser(User user) {
        mExecutor.execute(() -> {
            mRepository.createUser(user);
        });
    }

    public void removeUser(User user) {
        mExecutor.execute(() -> {
            mRepository.removeUser(user);
        });
    }

}
