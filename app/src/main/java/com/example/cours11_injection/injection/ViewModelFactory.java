package com.example.cours11_injection.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cours11_injection.repository.UserRepository;
import com.example.cours11_injection.view_model.UsersViewModel;

import java.util.concurrent.Executor;

/**
 * Permet de creer les differents viewModel en leurs passant directement tout les parametres qui leurs sont necessaires
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository userRepository;
    private final Executor executor;

    /**
     * Le constructor recoit tout les parametre dont auront besoin les viewModel
     * @param userRepository
     * @param executor
     */
    public ViewModelFactory(UserRepository userRepository, Executor executor) {
        this.userRepository = userRepository;
        this.executor = executor;
    }

    /**
     * Permet de creer les instances de viewModels
     * @param modelClass Type de l'instance du viewModel a creer
     * @return L'instnce de viewModel cree
     * (le type de l'objet retourne est T extends ViewModel ecla veut dire que ce doit etre un viewModel : une class quelle qu'elle soit qui herite de ViewModel)
     */
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(userRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}