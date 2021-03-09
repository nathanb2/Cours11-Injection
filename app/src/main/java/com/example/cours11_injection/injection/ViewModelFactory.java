package com.example.cours11_injection.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cours11_injection.repository.UserRepository;
import com.example.cours11_injection.view_model.UsersViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository userRepository;
    private final Executor executor;

    public ViewModelFactory(UserRepository userRepository, Executor executor) {
        this.userRepository = userRepository;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(userRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}