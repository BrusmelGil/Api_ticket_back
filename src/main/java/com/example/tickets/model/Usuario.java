package com.example.tickets.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombre;
    
    private String password;
    private int esTecnico;
    
    public static final int ES_TECNICO=1;
    public static final int NO_ES_TECNICO=0;
    
    /**
     * En JPA se requiere un constructor sin parámetros
     */
    public Usuario(){
        
    }
   
    public Usuario(String nombre, String password, int esTecnico) {
        this.nombre = nombre;
        this.password = password;
        this.esTecnico = esTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEsTecnico() {
        return esTecnico;
    }

    public void setEsTecnico(int esTecnico) {
        this.esTecnico = esTecnico;
    }

}
