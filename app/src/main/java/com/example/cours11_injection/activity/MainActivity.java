package com.example.cours11_injection.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.cours11_injection.R;
import com.example.cours11_injection.injection.Injection;
import com.example.cours11_injection.injection.ViewModelFactory;
import com.example.cours11_injection.model.User;
import com.example.cours11_injection.view_model.UsersViewModel;

import java.util.List;

/**
 * Affiche les donnees  ou en ajoute suite a un click utilisateur
 * Ne connait que userRepository et n'a aucune idee que userRepository amene le donnees depuis une DataBAse , ce n'est pas son probleme.
 * MainActivity demande des users ou indique en creer un au repository , celui ci se chargera de le faire realiser au responsable des donnees (ic la DB avec son DAO)
 * <p>
 * MainActivity peut reactualiser l'affichage des qu'il y a modification des donnees a afficher grace au LiveData voire ci-dessous
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on cree notre userViewmodel a l'aide de notre ViewModelFactory qui va se charger de creer toute les instances de classes necessaires au viewModel
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(this);
        UsersViewModel usersViewModel = mViewModelFactory.create(UsersViewModel.class);

        TextView textView = findViewById(R.id.AM_tv);

        usersViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            //onChanged est appele a chaque fois que la valeurs de getALlUsers change et recoit en parametre  la nouvelle valeur de getAllUsers
            @Override
            public void onChanged(List<User> users) {
                // Update the UI, in this case, a TextView.
                textView.setText(users.get(0).getName());
            }
        });

        findViewById(R.id.AM_change_name).setOnClickListener(v -> {

            LiveData<List<User>> usersLiveData = usersViewModel.getAllUsers();
            usersLiveData.observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    User user = users.get(0);
                    user.setName("Nathan");
                    usersViewModel.updateUser(user);
                    //remove l'observer du liveData une fois les donnees demandees recus car non pas
                    // observe pour affichage mais seulemnt pour recuperer les donnees que l'onsouhaite modifier au click
                    //this c'est l'instance de notre interface observer implemente que l'on avait ajoute a notre livedata pour l'observer
                    // et que l'on remove car ce n'est pls necessaire
                    usersLiveData.removeObserver(this);
                }
            });

        });

    }

}
