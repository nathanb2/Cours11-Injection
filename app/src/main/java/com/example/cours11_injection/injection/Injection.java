package com.example.cours11_injection.injection;

import android.content.Context;

import com.example.cours11_injection.data_base.DataBase;
import com.example.cours11_injection.repository.UserRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * class qui cree les instances de class necessaires aux differents viewModel du projet
 * L'objectif et que chaque class recoive directement tout ce dont elle a besoin et n'ai pas a recevoir de parametre qui lui serait necessaire a dans un deuxieme temps pouvoir construire les instance de ce dont elle a reeelement besoin
 * CHAQUE CLASS RECOIT CE DONT ELLE A BESOIN (EN PARAMETRE DE CONSTRUCTOR TOUTE LES VARIABLES QU'ELLE DECLARE AU NIVEAU DE LA CLASS)
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