package com.example.cours11_injection.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Grace a l'annotation Entity Room pourra creer une Table de DataBase a partire du model User
 */
@Entity
public class User {

    /**
     * indique a Room, lors de la creation d'une row user de genrer automatiquement une valeur pour la column id
     * Qui de plus est la primaryKey de la table User donc l'identifiant unique d'une row de User
     * (permet de differencie une row d'une autre et est donc utilise egalement comme identifiant pour etablire des relations dans la DB)
     */
    @PrimaryKey(autoGenerate=true)
    private long id;
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
