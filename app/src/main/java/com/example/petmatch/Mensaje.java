package com.example.petmatch;

public class Mensaje {

    private String mensaje, urlFoto, nombre, fotoPerfil, tipo_mensaje, hora;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String nombre, String fotoPerfil, String tipo_mensaje, String hora) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.tipo_mensaje = tipo_mensaje;
        this.hora = hora;
    }

    public Mensaje(String mensaje, String urlFoto, String nombre, String fotoPerfil, String tipo_mensaje, String hora) {
        this.mensaje = mensaje;
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.tipo_mensaje = tipo_mensaje;
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getTipo_mensaje() {
        return tipo_mensaje;
    }

    public void setTipo_mensaje(String tipo_mensaje) {
        this.tipo_mensaje = tipo_mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
