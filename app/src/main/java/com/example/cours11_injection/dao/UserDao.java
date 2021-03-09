package com.example.cours11_injection.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cours11_injection.model.User;

import java.util.List;

/**
 * Voir cours-8 pour son equivalent UserAPIService
 * Ici les annotations DAO permettent d'indiquer que les fonctiosn de cette interface peuvent etre realiser sur une base de donnees
 * Sur chaque fonction est ajoute l'annotation qui indique l'expression SQL a executer sur la base de donnees
 * Attention certaines annotations exige un certain format de fonction comme Inser return void par exemple
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user where id = :id")
    User getUser(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void removeUser(User user);

    /**
     * @return un LiveData qui permet donc de notifier toute modification de la valeur de retour de l'expression SQL
     * Donc en l'occurence la liste de user, soit l'ensemble des lignes de la table User
     * On peut donc observer les changements sur la DataBase et celui qui observe (MainActivity) peut reagire au changement et actualiser l'affichage
     */
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user where age >= 18")
    List<User> getMajorUsers();
}

