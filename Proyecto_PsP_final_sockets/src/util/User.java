package util;

import java.io.Serializable;

public class User implements Serializable {
    String nombre;
    String pass;

    public User(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

}
