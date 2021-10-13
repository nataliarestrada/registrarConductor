package com.example.registrarconductor;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Usuario implements Parcelable {
    private int dni, num_matricula, tipo_usuario;
    private String nombre, apellido, email, contrasenia;
    private String fecha_nacimiento;

    public Usuario() {

    }

    public Usuario(int dni, int num_matricula, int tipo_usuario, String nombre, String apellido, String email, String contrasenia, String fecha_nacimiento) {
        this.dni = dni;
        this.num_matricula = num_matricula;
        this.tipo_usuario = tipo_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getNum_matricula() {
        return num_matricula;
    }

    public void setNum_matricula(int num_matricula) {
        this.num_matricula = num_matricula;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return nombre + apellido;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dni);
        dest.writeInt(this.num_matricula);
        dest.writeInt(this.tipo_usuario);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.email);
        dest.writeString(this.contrasenia);
        dest.writeString(this.fecha_nacimiento);
    }

    protected Usuario(Parcel in) {
        this.dni = in.readInt();
        this.num_matricula = in.readInt();
        this.tipo_usuario = in.readInt();
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.email = in.readString();
        this.contrasenia = in.readString();
        this.fecha_nacimiento = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
