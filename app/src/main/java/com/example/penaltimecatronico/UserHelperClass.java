package com.example.penaltimecatronico;
//clase usada para sincronizar la informacion del usuario con la base de datos
public class UserHelperClass {
    private String username, email, password;
    public UserHelperClass() {

    }
    public UserHelperClass(String username, String email, String password) {
        this.username = username;//nombre de usuario del cliente
        this.email = email;//correo electronico del cliente
        this.password = password;//contrasenia del cliente
    }

//setters y getters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
