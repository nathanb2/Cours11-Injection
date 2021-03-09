package com.example.cours11_injection.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cours11_injection.dao.UserDao;
import com.example.cours11_injection.model.User;

/**
 * Class representant notre DataBase grace a l'annotation @Database et l'heritage de la class RoomDataBase
 * entities permet de declarer les differentes tables de notres Database,
 * la version seera a incrementer si des changements structurels sont effectuer comme modification ou ajout de table afin que room effectue la migration
 * Class sous DesignPattern SingleTon, on peut en creer qu'une instance car on veut une seul DB pour l'ensemble du projet
 */
@Database(entities = {User.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    private static com.example.cours11_injection.data_base.DataBase INSTANCE;

    /**
     * @return une sorte d'implementation de l'interface userDao dasn notre class DataBase qui permetra
     * d'appeler les differentes fonctions de notre DAO sur la DataBase et d'y executer les fonctions (leur equivalent en expression SQL)
     */
    public abstract UserDao userDao();

    public static com.example.cours11_injection.data_base.DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.cours11_injection.data_base.DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            com.example.cours11_injection.data_base.DataBase.class, "database")
                            .addCallback(sOnOpenCallback)//permet d'ajouter un callback a l'ouverture de la DB voire ci-dessous
                            .fallbackToDestructiveMigration()
                            .build();//retourne l'instance de la DB avec les differentes fonctionnalites ajoutees dans le builder ci-dessu
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Permet de realiser une action comme peupler la base lors de sa creation
     */
    private static final RoomDatabase.Callback sOnOpenCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    String count = "SELECT count(*) FROM user";
                    Cursor cursor = db.query(count, null);
                    cursor.moveToFirst();
                    int cursorCount = cursor.getInt(0);
                    //permet de verifier si la table est vide et donc de la peupler qu'a la creation et non a toute ouverture de la DB
                    if (cursorCount <= 0) {
                        for (int i = 0; i < 10; i++) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("name", "Philippe" + i);
                            contentValues.put("age", String.valueOf(20 + i));

                            db.insert("User", OnConflictStrategy.REPLACE, contentValues);
                        }
                    }
                }
            };

}