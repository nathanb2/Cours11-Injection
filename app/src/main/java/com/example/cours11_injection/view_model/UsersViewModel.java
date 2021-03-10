package com.example.cours11_injection.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cours11_injection.model.User;
import com.example.cours11_injection.repository.UserRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class UsersViewModel extends ViewModel {
    /**
     * executor permet d'executer un runnable (block de code a runner lorsqu'on l'indique) sur un autre thread
     * et donc de ne plus realiser les actions de la base de donnees sur le MainThread car peut bloquer le UI (action qui prend du temps)
     * grace a cela on a put enelever de la configuration de la database la fonction qui lui permetait de realiser des actions sur le mainthread (disable par defaut) car ce n'est pas sence etre rendu possible
     */
    private final Executor mExecutor;
    private UserRepository mRepository;

    public UsersViewModel(UserRepository userRepository, Executor executor) {
        mRepository = userRepository;
        mExecutor = executor;
    }

    public LiveData<List<User>> getAllUsers() {
        //le live data realise deja ses actions de recuperation de donnees de la database sur un autre thread donc il n'est pas necessaire d'utiliser d'executor
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
